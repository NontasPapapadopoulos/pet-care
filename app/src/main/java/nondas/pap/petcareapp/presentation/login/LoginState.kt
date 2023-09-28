package nondas.pap.petcareapp.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoginButtonEnabled: Boolean = false

)
