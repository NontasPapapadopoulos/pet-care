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
import nondas.pap.petcareapp.presentation.ValidatedField
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
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavController,
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()



    RegisterContent(
        name = state.name,
        email = state.email,
        password = state.password,
        confirmPassword = state.confirmPassword,
        onNameEntered = { viewModel.add(RegisterEvent.NameEntered(it)) },
        onEmailEntered = { viewModel.add(RegisterEvent.EmailEntered(it)) },
        onPasswordEntered = { viewModel.add(RegisterEvent.PasswordEntered(it)) },
        onConfirmPasswordEntered = { viewModel.add(RegisterEvent.ConfirmPasswordEntered(it)) } ,
        onRegisterButtonClicked = { viewModel.add(RegisterEvent.RegisterButtonClicked) },
        onCancelButtonClicked = { navController.popBackStack() },
        isRegisterButtonEnabled = state.isRegisterButtonEnabled
    )


}

@Composable
private fun RegisterContent(
    name: ValidatedField,
    email: ValidatedField,
    password: ValidatedField,
    confirmPassword: ValidatedField,
    onNameEntered: (String) -> Unit,
    onEmailEntered: (String) -> Unit,
    onPasswordEntered: (String) -> Unit,
    onConfirmPasswordEntered: (String) -> Unit,
    onRegisterButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    isRegisterButtonEnabled: Boolean,
) {

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
            inputValue = name.value,
            isValidationSuccessful = name.validation.isSuccessful,
            errorMessage = name.validation.errorMessage,
            valueEntered = { onNameEntered(it) },
        )

        AddVerticalSpace()

        InputText(
            placeholder = "Email",
            inputValue = email.value,
            isValidationSuccessful = email.validation.isSuccessful,
            errorMessage = email.validation.errorMessage,
            valueEntered = { onEmailEntered(it) },
        )

        AddVerticalSpace()

        InputText(
            placeholder = "Password",
            inputValue = password.value,
            isValidationSuccessful = password.validation.isSuccessful,
            errorMessage = password.validation.errorMessage,
            valueEntered = { onPasswordEntered(it) },

            )

        AddVerticalSpace()

        InputText(
            placeholder = "Confirm password",
            inputValue = confirmPassword.value,
            isValidationSuccessful = confirmPassword.validation.isSuccessful,
            errorMessage = confirmPassword.validation.errorMessage,
            valueEntered = { onConfirmPasswordEntered(it) },
        )



        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            buttonTitle = "register",
            onButtonClicked = { onRegisterButtonClicked() },
            isEnabled = isRegisterButtonEnabled,
            backgroundColor = R.color.light_green,
            textColor = R.color.white,
            hasBorder = false
        )

        AddVerticalSpace()

        SecondaryButton(
            buttonTitle = "cancel",
            onButtonClicked = { onCancelButtonClicked() },
            backgroundColor = R.color.pink,
            textColor = R.color.white,
            hasBorder = false
        )

        AddVerticalSpace(20)
    }
}

@Composable
@Preview
fun RegisterContentPreview() {
    RegisterContent(
        name = ValidatedField(),
        email = ValidatedField(),
        password = ValidatedField(),
        confirmPassword = ValidatedField() ,
        onNameEntered = {},
        onEmailEntered = {},
        onPasswordEntered = {},
        onConfirmPasswordEntered = {},
        onRegisterButtonClicked = {},
        onCancelButtonClicked = {},
        isRegisterButtonEnabled = true
    )
}