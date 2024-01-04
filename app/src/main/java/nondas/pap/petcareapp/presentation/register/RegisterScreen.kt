package nondas.pap.petcareapp.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    navController: NavController,
) {

    val viewModel: RegisterViewModel = hiltViewModel()

    val state by viewModel.uiState.collectAsStateWithLifecycle()
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
            inputValue = state.name,
            isValidationSuccessful = state.nameValidation.isSuccessful,
            errorMessage = state.nameValidation.errorMessage,
            valueEntered = { viewModel.add(RegisterEvent.NameEntered(it)) },
        )

        AddVerticalSpace()

        InputText(
            placeholder = "Email",
            inputValue = state.email,
            isValidationSuccessful = state.emailValidation.isSuccessful,
            errorMessage = state.emailValidation.errorMessage,
            valueEntered = { viewModel.add(RegisterEvent.EmailEntered(it)) },
        )

        AddVerticalSpace()

        InputText(
            placeholder = "Password",
            inputValue = state.password,
            isValidationSuccessful = state.passwordValidation.isSuccessful,
            errorMessage = state.passwordValidation.errorMessage,
            valueEntered = { viewModel.add(RegisterEvent.PasswordEntered(it)) },

        )

        AddVerticalSpace()

        InputText(
            placeholder = "Confirm password",
            inputValue = state.confirmPassword,
            isValidationSuccessful = state.confirmPasswordValidation.isSuccessful,
            errorMessage = state.confirmPasswordValidation.errorMessage,
            valueEntered = { viewModel.add(RegisterEvent.ConfirmPasswordEntered(it)) },
        )



        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            buttonTitle = "register",
            onButtonClicked = { viewModel.add(RegisterEvent.RegisterButtonClicked) },
            isEnabled = state.isRegisterButtonEnabled,
            backgroundColor = R.color.light_green,
            textColor = R.color.white,
            hasBorder = false
        )

        AddVerticalSpace()

        SecondaryButton(
            buttonTitle = "cancel",
            onButtonClicked = { navController.popBackStack() },
            backgroundColor = R.color.pink,
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