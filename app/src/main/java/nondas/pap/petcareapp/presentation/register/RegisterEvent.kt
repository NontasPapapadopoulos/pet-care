package nondas.pap.petcareapp.presentation.register

sealed class RegisterEvent {
    data class EmailEntered(val userInput: String): RegisterEvent()
    data class NameEntered(val userInput: String): RegisterEvent()
    data class PasswordEntered(val userInput: String): RegisterEvent()
    data class ConfirmPasswordEntered(val userInput: String): RegisterEvent()

    object RegisterButtonClicked: RegisterEvent()
}
