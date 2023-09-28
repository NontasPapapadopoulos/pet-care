package nondas.pap.petcareapp.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(

): ViewModel() {


    var state by mutableStateOf(RegisterState())


    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.NameEntered -> {
                state = state.copy(name = event.userInput)
            }

            is RegisterEvent.EmailEntered -> {
                state = state.copy(email = event.userInput)
            }

            is RegisterEvent.ConfirmPasswordEntered -> {
                state = state.copy(confirmPassword = event.userInput)
            }


            is RegisterEvent.PasswordEntered -> {
                state = state.copy(password = event.userInput)
            }

            is RegisterEvent.RegisterButtonClicked -> {}
        }
    }

}


