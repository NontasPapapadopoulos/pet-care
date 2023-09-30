package nondas.pap.petcareapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import nondas.pap.petcareapp.presentation.component.AddVerticalSpace
import nondas.pap.petcareapp.presentation.component.MyTitle
import nondas.pap.petcareapp.presentation.component.PrimaryButton
import nondas.pap.petcareapp.presentation.component.SecondaryButton
import nondas.pap.petcareapp.presentation.component.inputText.InputText
import nondas.pap.petcareapp.presentation.login.LoginEvent
import nondas.pap.petcareapp.presentation.register.RegisterEvent
import nondas.pap.petcareapp.presentation.register.RegisterViewModel

@Composable
fun RegisterScreen(
    navController: NavController
) {

    val viewModel: RegisterViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.mpez))
    ) {

        AddVerticalSpace(50)

        MyTitle(
            title = "Register to Pet Care",
            textColor = R.color.dark_red
        )


        AddVerticalSpace(150)

        InputText(
            placeholder = "Name",
            inputValue = viewModel.state.name,
            valueEntered = { viewModel.onEvent(RegisterEvent.NameEntered(it)) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        AddVerticalSpace()

        InputText(
            placeholder = "Email",
            inputValue = viewModel.state.email,
            valueEntered = { viewModel.onEvent(RegisterEvent.EmailEntered(it)) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        AddVerticalSpace()

        InputText(
            placeholder = "Password",
            inputValue = viewModel.state.password,
            valueEntered = { viewModel.onEvent(RegisterEvent.PasswordEntered(it)) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        AddVerticalSpace()

        InputText(
            placeholder = "Confirm password",
            inputValue = viewModel.state.confirmPassword,
            valueEntered = { viewModel.onEvent(RegisterEvent.ConfirmPasswordEntered(it)) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )



        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            buttonTitle = "register",
            onButtonClicked = { viewModel.onEvent(RegisterEvent.RegisterButtonClicked) },
            backgroundColor = R.color.oil_green,
            textColor = R.color.white,
            hasBorder = false
        )

        AddVerticalSpace()

        SecondaryButton(
            buttonTitle = "cancel",
            onButtonClicked = { navController.popBackStack() },
            backgroundColor = R.color.dark_red,
            textColor = R.color.white,
            hasBorder = false
        )

        AddVerticalSpace(20)

    }

}


@Composable
@Preview
fun RegisterScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}