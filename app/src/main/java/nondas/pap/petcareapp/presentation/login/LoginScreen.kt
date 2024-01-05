package nondas.pap.petcareapp.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.infastracture.navigation.screen.PETS_ROUTE
import nondas.pap.petcareapp.infastracture.navigation.screen.Screen
import nondas.pap.petcareapp.presentation.UIEvent
import nondas.pap.petcareapp.presentation.component.AddVerticalSpace
import nondas.pap.petcareapp.presentation.component.ManageSystemBars
import nondas.pap.petcareapp.presentation.component.OutLinedInputText
import nondas.pap.petcareapp.presentation.component.PrimaryButton
import nondas.pap.petcareapp.presentation.component.SecondaryButton

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest {event ->
            if (event is UIEvent.Navigate)
                navController.navigate(route = PETS_ROUTE)
        }
    }
    ManageSystemBars()


    LoginContent(
        email = state.email,
        password = state.password,
        onEmailEntered = { viewModel.add(LoginEvent.EmailEntered(it)) },
        onPasswordEntered = { viewModel.add(LoginEvent.PasswordEntered(it)) },
        onLoginButtonClicked = { viewModel.add(LoginEvent.LoginButtonClicked(state.email, state.password)) },
        navController = navController
    )

}


@Composable
private fun LoginContent(
    email: String,
    password: String,
    onEmailEntered: (String) -> Unit,
    onPasswordEntered: (String) -> Unit,
    onLoginButtonClicked: () -> Unit,
    navController: NavController
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        AddVerticalSpace(50)

        Text(
            text = "Pet Care",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
        )

        AddVerticalSpace(190)

        OutLinedInputText(
            label = "Email",
            inputValue = email,
            valueEntered = { onEmailEntered(it) },
        )

        AddVerticalSpace(20)

        OutLinedInputText(
            label = "Password",
            inputValue = password,
            valueEntered = { onPasswordEntered(it) },
        )

        AddVerticalSpace(20)



        PrimaryButton(
            buttonTitle = "login",
            onButtonClicked = { onLoginButtonClicked() },
            backgroundColor = R.color.pink,
            textColor = R.color.white,
            hasBorder = false
        )

        AddVerticalSpace()

        SecondaryButton(
            buttonTitle = "register",
            onButtonClicked = { navController.navigate(route = Screen.Reports.route) },
            backgroundColor = R.color.oil_green,
            textColor = R.color.white,
            hasBorder = false,
        )
    }
}

@Composable
@Preview
private fun LoginContentPreview() {
    LoginContent(
        email = "",
        password = "",
        onEmailEntered = {},
        onPasswordEntered = {},
        onLoginButtonClicked = {},
        navController = rememberNavController()
    )
}