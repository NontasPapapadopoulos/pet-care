package nondas.pap.petcareapp.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nondas.pap.petcareapp.presentation.login.LoginEvent
import nondas.pap.petcareapp.presentation.login.LoginState
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(

): ViewModel() {

    var state by mutableStateOf(LoginState())


    init {


    }


    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.EmailEntered -> {
                state = state.copy(email = event.userInput)
            }

            is LoginEvent.PasswordEntered -> {
                state = state.copy(password = event.userInput)

            }

            is LoginEvent.LoginButtonClicked -> {}
        }
    }



}