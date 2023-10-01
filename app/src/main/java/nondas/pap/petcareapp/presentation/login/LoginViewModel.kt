package nondas.pap.petcareapp.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import nondas.pap.petcareapp.presentation.UIEvent
import nondas.pap.petcareapp.presentation.login.LoginEvent
import nondas.pap.petcareapp.presentation.login.LoginState
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(

): ViewModel() {

    var state by mutableStateOf(LoginState())

    private val _uiEventFlow = MutableSharedFlow<UIEvent>()
    val uiEvent: SharedFlow<UIEvent> = _uiEventFlow.asSharedFlow()


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

            is LoginEvent.LoginButtonClicked -> {
                viewModelScope.launch {
                    _uiEventFlow.emit(UIEvent.Navigate)
                }
            }

        }
    }



}