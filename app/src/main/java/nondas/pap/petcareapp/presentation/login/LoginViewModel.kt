package nondas.pap.petcareapp.presentation.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import nondas.pap.petcareapp.domain.usecase.user.PerformLogin
import nondas.pap.petcareapp.presentation.BlocViewModel
import nondas.pap.petcareapp.presentation.Handler
import nondas.pap.petcareapp.presentation.UIEvent
import nondas.pap.petcareapp.presentation.flatMapMergeWith
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val performLogin: PerformLogin
): BlocViewModel<LoginEvent, LoginState>() {

    private val emailFlow = MutableSharedFlow<String>()
    private val passwordFlow = MutableSharedFlow<String>()
    private val loginHandler = MutableSharedFlow<Handler.Event<PerformLogin.Params>>()

    override val _uiState: StateFlow<LoginState> = combine(
        loginHandler.flatMapMergeWith(performLogin).onStart { emit(Handler.State.Idle) },
        emailFlow.onStart { emit("") },
        passwordFlow.onStart { emit("") },
    ) { loginState, email, password,  ->

        val isLoading = loginState is Handler.State.Running

        LoginState(
            email = email,
            password = password,
            isLoading = isLoading
        )

    }.stateIn(
        scope = viewModelScope,
        initialValue = LoginState(),
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
    )


    private val _uiEventFlow = MutableSharedFlow<UIEvent>()
    val uiEvent: SharedFlow<UIEvent> = _uiEventFlow.asSharedFlow()


    init {

        on(LoginEvent.EmailEntered::class) {
            emailFlow.emit(it.userInput)
        }

        on(LoginEvent.PasswordEntered::class) {
            passwordFlow.emit(it.userInput)
        }

        on(LoginEvent.LoginButtonClicked::class) {
            loginHandler.emit(Handler.Event.Execute(PerformLogin.Params(it.email, it.password)))
        }

    }


}

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false

)

sealed class LoginEvent {
    data class EmailEntered(val userInput: String): LoginEvent()
    data class PasswordEntered(val userInput: String): LoginEvent()
    data class LoginButtonClicked(val email: String, val password: String): LoginEvent()

}


