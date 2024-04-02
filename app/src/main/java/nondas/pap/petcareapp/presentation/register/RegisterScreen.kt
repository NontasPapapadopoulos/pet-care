package nondas.pap.petcareapp.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.presentation.ValidatedField
import nondas.pap.petcareapp.presentation.component.VerticalSpace
import nondas.pap.petcareapp.presentation.component.OutLinedInputText
import nondas.pap.petcareapp.presentation.component.PrimaryButton
import nondas.pap.petcareapp.presentation.component.SecondaryButton


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
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        VerticalSpace(50)

        Text(
            text = "Register to Pet Care",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center
        )


        VerticalSpace(100)

        OutLinedInputText(
            label = "Name",
            inputValue = name.value,
            validationResult = name.validation,
            valueEntered = { onNameEntered(it) },
        )

        VerticalSpace()

        OutLinedInputText(
            label = "Email",
            inputValue = email.value,
            validationResult = email.validation,
            valueEntered = { onEmailEntered(it) },
        )

        VerticalSpace()

        OutLinedInputText(
            label = "Password",
            inputValue = password.value,
            validationResult = password.validation,
            valueEntered = { onPasswordEntered(it) }
        )

        VerticalSpace()

        OutLinedInputText(
            label = "Confirm password",
            inputValue = confirmPassword.value,
            validationResult = confirmPassword.validation,
            valueEntered = { onConfirmPasswordEntered(it) }
        )


        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            buttonTitle = "register",
            onButtonClicked = { onRegisterButtonClicked() },
            isEnabled = true,
            backgroundColor = R.color.light_green,
            textColor = R.color.white,
            hasBorder = false
        )

        VerticalSpace()

        SecondaryButton(
            buttonTitle = "cancel",
            onButtonClicked = { onCancelButtonClicked() },
            backgroundColor = R.color.pink,
            textColor = R.color.white,
            hasBorder = false
        )

        VerticalSpace(20)
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