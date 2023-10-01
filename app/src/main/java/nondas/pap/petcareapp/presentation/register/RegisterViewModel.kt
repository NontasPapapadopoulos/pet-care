package nondas.pap.petcareapp.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nondas.pap.petcareapp.domain.ConfirmPasswordValidator
import nondas.pap.petcareapp.domain.EmailValidator
import nondas.pap.petcareapp.domain.NameValidator
import nondas.pap.petcareapp.domain.PasswordValidator
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val nameValidator: NameValidator,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val confirmPasswordValidator: ConfirmPasswordValidator
): ViewModel() {


    var state by mutableStateOf(RegisterState())


    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.NameEntered -> {
                val validationResult = nameValidator.execute(event.userInput)

                state = state.copy(
                    name = event.userInput,
                    nameValidation = validationResult
                )
            }

            is RegisterEvent.EmailEntered -> {
                val validationResult = emailValidator.execute(event.userInput)

                state = state.copy(
                    email = event.userInput,
                    emailValidation = validationResult
                )
            }


            is RegisterEvent.PasswordEntered -> {
                val validationResult = passwordValidator.execute(event.userInput)

                state = state.copy(
                    password = event.userInput,
                    passwordValidation = validationResult
                )
            }

            is RegisterEvent.ConfirmPasswordEntered -> {
                val validationResult = confirmPasswordValidator.execute(
                    password = state.password,
                    confirmPassword = event.userInput
                )

                state = state.copy(
                    confirmPassword = event.userInput,
                    confirmPasswordValidation = validationResult
                )
            }



            is RegisterEvent.RegisterButtonClicked -> {
                performRegister()
            }

        }
        enableRegisterButton()

    }

    private fun performRegister() {
        TODO("Not yet implemented")
    }

    private fun enableRegisterButton() {
        val isFormValid = state.emailValidation.isSuccessful &&
                state.nameValidation.isSuccessful &&
                state.passwordValidation.isSuccessful &&
                state.confirmPasswordValidation.isSuccessful

        state = state.copy(isRegisterButtonEnabled = isFormValid)
    }

}


