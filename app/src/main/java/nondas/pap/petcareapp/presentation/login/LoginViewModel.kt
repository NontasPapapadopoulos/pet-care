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
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val performLogin: PerformLogin
): BlocViewModel<LoginEvent, LoginState>() {

    private val emailFlow = MutableSharedFlow<String>()
    private val passwordFlow = MutableSharedFlow<String>()

    private val isLoading = MutableSharedFlow<Boolean>()

    private val _navigationFlow = MutableSharedFlow<Boolean>()
    val navigationFlow: SharedFlow<Boolean> = _navigationFlow.asSharedFlow()


    override val _uiState: StateFlow<LoginState> = combine(
        emailFlow.onStart { emit("") },
        passwordFlow.onStart { emit("") },
        isLoading.onStart { emit(false) }
    ) { email, password, isLoading  ->


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



    init {

        on(LoginEvent.EmailEntered::class) {
            emailFlow.emit(it.userInput)
        }

        on(LoginEvent.PasswordEntered::class) {
            passwordFlow.emit(it.userInput)
        }

        on(LoginEvent.LoginButtonClicked::class) {
            isLoading.emit(true)
            performLogin.execute(params = PerformLogin.Params(it.email, it.password)).fold(
                onSuccess = { _navigationFlow.emit(true) },
                onFailure = { addError(it) }
            )
            isLoading.emit(false)
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


