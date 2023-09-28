package nondas.pap.petcareapp.presentation.login

sealed class LoginEvent {
    data class EmailEntered(val userInput: String): LoginEvent()
    data class PasswordEntered(val userInput: String): LoginEvent()

    object LoginButtonClicked: LoginEvent()

}

