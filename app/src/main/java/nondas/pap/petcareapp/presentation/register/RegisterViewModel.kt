package nondas.pap.petcareapp.presentation.register

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import nondas.pap.petcareapp.domain.entity.UserDomainEntity
import nondas.pap.petcareapp.domain.usecase.user.PerformRegister
import nondas.pap.petcareapp.domain.usecase.validator.ConfirmPasswordValidator
import nondas.pap.petcareapp.domain.usecase.validator.EmailValidator
import nondas.pap.petcareapp.domain.usecase.validator.NameValidator
import nondas.pap.petcareapp.domain.usecase.validator.PasswordValidator
import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult
import nondas.pap.petcareapp.presentation.BlocViewModel
import nondas.pap.petcareapp.presentation.Handler
import nondas.pap.petcareapp.presentation.ValidatedField
import nondas.pap.petcareapp.presentation.flatMapMergeWith
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

    private val registerHandler = MutableSharedFlow<Handler.Event<PerformRegister.Params>>()
    private val registerFlow = registerHandler.flatMapMergeWith(performRegister).onEach {
        if (it is Handler.State.Success) {}
            // navigate
    }

    override val _uiState: StateFlow<RegisterState> = combine(
        nameTextFlow.onStart { emit(ValidatedField(value = "", ValidationResult(isSuccessful = true))) },
        emailTextFlow.onStart { emit(ValidatedField(value = "", ValidationResult(isSuccessful = true))) },
        passwordTextFlow.onStart { emit(ValidatedField(value = "", ValidationResult(isSuccessful = true))) },
        confirmPasswordTextFlow.onStart { emit(ValidatedField(value = "", ValidationResult(isSuccessful = true))) },
        registerButtonStatusFlow.onStart { emit(false) },
//        registerFlow.onStart { emit(Handler.State.Idle) }
    ) {  name, email, password, confirmPassword, buttonStatus ->


        RegisterState(
            name = name,
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            isRegisterButtonEnabled = buttonStatus,
            isLoading = false
        )

    }.stateIn(
        scope = viewModelScope,
        initialValue = RegisterState(),
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
    )

    init {
        on(RegisterEvent.NameEntered::class) {
            val validationResult = nameValidator.execute(it.userInput)
            val validatedField = ValidatedField(value = it.userInput, validationResult)
            nameTextFlow.emit(validatedField)
            enableRegisterButton()
        }

        on(RegisterEvent.EmailEntered::class) {
            val validationResult = emailValidator.execute(it.userInput)
            val validatedField = ValidatedField(value = it.userInput, validationResult)
            emailTextFlow.emit(validatedField)
            enableRegisterButton()
        }

        on(RegisterEvent.PasswordEntered::class) {
            val validationResult = passwordValidator.execute(it.userInput)
            val validatedField = ValidatedField(value = it.userInput, validationResult)
            passwordTextFlow.emit(validatedField)
            enableRegisterButton()
        }

        on(RegisterEvent.ConfirmPasswordEntered::class) {
            val validationResult = confirmPasswordValidator.execute(
                password = uiState.value.password.value,
                confirmPassword = it.userInput
            )
            val validatedField = ValidatedField(value = it.userInput, validationResult)
            confirmPasswordTextFlow.emit(validatedField)
            enableRegisterButton()
        }

        on(RegisterEvent.RegisterButtonClicked::class) {
            val userDomainEntity = UserDomainEntity(
                email = uiState.value.email.value,
                password = uiState.value.password.value,
                name = uiState.value.name.value,
            )

            registerHandler.emit(Handler.Event.Execute(PerformRegister.Params(userDomainEntity)))
            enableRegisterButton()
        }
    }


    private suspend fun enableRegisterButton() {
        val isFormValid = uiState.value.email.validation.isSuccessful &&
                uiState.value.name.validation.isSuccessful &&
                uiState.value.password.validation.isSuccessful &&
                uiState.value.confirmPassword.validation.isSuccessful &&
                !uiState.value.isLoading

        registerButtonStatusFlow.emit(isFormValid)
    }

}

data class RegisterState(
    val name: ValidatedField = ValidatedField(),
    val email: ValidatedField = ValidatedField(),
    val password: ValidatedField = ValidatedField(),
    val confirmPassword: ValidatedField = ValidatedField(),
    val isRegisterButtonEnabled: Boolean = false,
    val isLoading: Boolean = false
)


sealed class RegisterEvent {
    data class EmailEntered(val userInput: String): RegisterEvent()
    data class NameEntered(val userInput: String): RegisterEvent()
    data class PasswordEntered(val userInput: String): RegisterEvent()
    data class ConfirmPasswordEntered(val userInput: String): RegisterEvent()
    object RegisterButtonClicked: RegisterEvent()
}
