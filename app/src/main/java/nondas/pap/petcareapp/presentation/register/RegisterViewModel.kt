package nondas.pap.petcareapp.presentation.register

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import nondas.pap.petcareapp.domain.entity.UserDomainEntity
import nondas.pap.petcareapp.domain.usecase.user.PerformRegister
import nondas.pap.petcareapp.domain.usecase.validator.ConfirmPasswordValidator
import nondas.pap.petcareapp.domain.usecase.validator.EmailValidator
import nondas.pap.petcareapp.domain.usecase.validator.NameValidator
import nondas.pap.petcareapp.domain.usecase.validator.PasswordValidator
import nondas.pap.petcareapp.presentation.BlocViewModel
import nondas.pap.petcareapp.presentation.ValidatedField
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val nameValidator: NameValidator,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val confirmPasswordValidator: ConfirmPasswordValidator,
    private val performRegister: PerformRegister
): BlocViewModel<RegisterEvent, RegisterState>() {

    private val nameTextFlow = MutableSharedFlow<ValidatedField>()
    private val emailTextFlow = MutableSharedFlow<ValidatedField>()
    private val passwordTextFlow = MutableSharedFlow<ValidatedField>()
    private val confirmPasswordTextFlow = MutableSharedFlow<ValidatedField>()
    private val registerButtonStatusFlow = MutableSharedFlow<Boolean>()


    override val _uiState: StateFlow<RegisterState> = combine(
        nameTextFlow.onStart { emit(ValidatedField()) },
        emailTextFlow.onStart { emit(ValidatedField()) },
        passwordTextFlow.onStart { emit(ValidatedField()) },
        confirmPasswordTextFlow.onStart { emit(ValidatedField()) },
        registerButtonStatusFlow.onStart { emit(false) },
    ) { name, email, password, confirmPassword, buttonStatus ->

        RegisterState.Content(
            name = name,
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            isRegisterButtonEnabled = buttonStatus,
            isLoading = false
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = RegisterState.Idle
    )

    init {
        on(RegisterEvent.NameEntered::class) {
            val validationResult = nameValidator.validate(it.userInput)
            val validatedField = ValidatedField(value = it.userInput, validationResult)
            nameTextFlow.emit(validatedField)
            enableRegisterButton()
        }

        on(RegisterEvent.EmailEntered::class) {
            val validationResult = emailValidator.validate(it.userInput)
            val validatedField = ValidatedField(value = it.userInput, validationResult)
            emailTextFlow.emit(validatedField)
            enableRegisterButton()
        }

        on(RegisterEvent.PasswordEntered::class) {
            val validationResult = passwordValidator.validate(it.userInput)
            val validatedField = ValidatedField(value = it.userInput, validationResult)
            passwordTextFlow.emit(validatedField)
            enableRegisterButton()
        }

        on(RegisterEvent.ConfirmPasswordEntered::class) { event ->
            onState<RegisterState.Content> {
                val validationResult = confirmPasswordValidator.validate(
                    password = it.password.value,
                    confirmPassword = event.userInput
                )
                val validatedField = ValidatedField(value = event.userInput, validationResult)
                confirmPasswordTextFlow.emit(validatedField)
                enableRegisterButton()
            }
        }

        on(RegisterEvent.RegisterButtonClicked::class) {
            onState<RegisterState.Content> {
                val userDomainEntity = UserDomainEntity(
                    email = it.email.value,
                    password = it.password.value,
                    name = it.name.value,
                    userId = ""
                )
                performRegister.execute(PerformRegister.Params(userDomainEntity))
            }
            enableRegisterButton()
        }
    }


    private suspend fun enableRegisterButton() {
        onState<RegisterState.Content> {
            val isFormValid = !it.email.validation.isError &&
                    !it.name.validation.isError &&
                    !it.password.validation.isError &&
                    !it.confirmPassword.validation.isError &&
                    !it.isLoading

            registerButtonStatusFlow.emit(isFormValid)
        }

    }

}

sealed interface RegisterState {
    object Idle: RegisterState
    data class Content(
        val name: ValidatedField,
        val email: ValidatedField,
        val password: ValidatedField,
        val confirmPassword: ValidatedField,
        val isRegisterButtonEnabled: Boolean,
        val isLoading: Boolean
    ): RegisterState
}



sealed class RegisterEvent {
    data class EmailEntered(val userInput: String): RegisterEvent()
    data class NameEntered(val userInput: String): RegisterEvent()
    data class PasswordEntered(val userInput: String): RegisterEvent()
    data class ConfirmPasswordEntered(val userInput: String): RegisterEvent()
    object RegisterButtonClicked: RegisterEvent()
}
