package nondas.pap.petcareapp.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.infastracture.navigation.Screen
import nondas.pap.petcareapp.presentation.component.AddVerticalSpace
import nondas.pap.petcareapp.presentation.component.MyTitle
import nondas.pap.petcareapp.presentation.component.PrimaryButton
import nondas.pap.petcareapp.presentation.component.SecondaryButton
import nondas.pap.petcareapp.presentation.component.inputText.InputText

@Composable
fun LoginScreen(
    navController: NavController
) {

    val viewModel: LoginViewModel = hiltViewModel()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mpez))
    ) {


        AddVerticalSpace(50)

        MyTitle(
            title = "Pet Care",
            textColor = R.color.dark_red
        )

        AddVerticalSpace(190)

        InputText(
            placeholder = "Email",
            inputValue = viewModel.state.email,
            valueEntered = { viewModel.onEvent(LoginEvent.EmailEntered(it)) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        AddVerticalSpace(20)

        InputText(
            placeholder = "Password",
            inputValue = viewModel.state.password,
            valueEntered = { viewModel.onEvent(LoginEvent.PasswordEntered(it)) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        AddVerticalSpace(20)

        PrimaryButton(
            buttonTitle = "login",
            onButtonClicked = { viewModel.onEvent(LoginEvent.LoginButtonClicked) },
            backgroundColor = R.color.pink,
            textColor = R.color.white,
            hasBorder = false,

        )

        AddVerticalSpace()

        SecondaryButton(
            buttonTitle = "register",
            onButtonClicked = { navController.navigate(route = Screen.RegisterScreen.route) },
            backgroundColor = R.color.oil_green,
            textColor = R.color.white,
            hasBorder = false,

        )
    }
}


@Composable
@Preview
private fun LoginPreview() {
    LoginScreen(navController = rememberNavController())
}