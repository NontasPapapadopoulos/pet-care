package nondas.pap.petcareapp.presentation.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import nondas.pap.petcareapp.presentation.ValidatedField
import nondas.pap.petcareapp.presentation.component.LoadingBox
import nondas.pap.petcareapp.presentation.component.VerticalSpace
import nondas.pap.petcareapp.ui.theme.PetCareAppTheme


@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.errorFlow.collect { error ->
            Toast.makeText(
                context,
                error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(val state = uiState) {
        is RegisterState.Content -> {
            RegisterContent(
                content = state,
                onNameEntered = { viewModel.add(RegisterEvent.NameEntered(it)) },
                onEmailEntered = { viewModel.add(RegisterEvent.EmailEntered(it)) },
                onPasswordEntered = { viewModel.add(RegisterEvent.PasswordEntered(it)) },
                onConfirmPasswordEntered = { viewModel.add(RegisterEvent.ConfirmPasswordEntered(it)) } ,
                onRegisterButtonClicked = { viewModel.add(RegisterEvent.RegisterButtonClicked) },
                onCancelButtonClicked = { onNavigateBack() },
            )
        }
        RegisterState.Idle -> {
            LoadingBox()
        }
    }




}

@Composable
private fun RegisterContent(
    content: RegisterState.Content,
    onNameEntered: (String) -> Unit,
    onEmailEntered: (String) -> Unit,
    onPasswordEntered: (String) -> Unit,
    onConfirmPasswordEntered: (String) -> Unit,
    onRegisterButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
) {

    Scaffold {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top = 50.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Register",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                Icons.Default.Pets,
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.weight(1f))


            OutlinedTextField(
                value = content.name.value,
                onValueChange = { onNameEntered(it) },
                label = { Text(text = "Name") },
                trailingIcon = {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                    )
                },
                isError = content.name.validation.isError,
                supportingText = {
                     if (content.name.validation.isError) {
                         Text(text = content.name.validation.errorMessage)
                     }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                value = content.email.value,
                onValueChange = { onEmailEntered(it) },
                label = { Text(text = "Email") },
                trailingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = null,
                    )
                },
                isError = content.email.validation.isError,
                supportingText = {
                    if (content.email.validation.isError) {
                        Text(text = content.email.validation.errorMessage)
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = content.password.value,
                onValueChange = { onPasswordEntered(it) },
                label = { Text(text = "Password") },
                trailingIcon = {
                    Icon(
                        Icons.Default.Key,
                        contentDescription = null,
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                isError = content.password.validation.isError,
                supportingText = {
                    if (content.password.validation.isError) {
                        Text(text = content.password.validation.errorMessage)
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                value = content.confirmPassword.value,
                onValueChange = { onConfirmPasswordEntered(it) },
                label = { Text(text = "Confirm Password") },
                trailingIcon = {
                    Icon(
                        Icons.Default.Key,
                        contentDescription = null,
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                isError = content.confirmPassword.validation.isError,
                supportingText = {
                    if (content.confirmPassword.validation.isError) {
                        Text(text = content.confirmPassword.validation.errorMessage)
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onRegisterButtonClicked() },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Register")
            }

            VerticalSpace()

            Button(
                onClick = { onCancelButtonClicked() },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Cancel")
            }
        }
    }

}

@Composable
@Preview
fun RegisterContentPreview() {
    PetCareAppTheme {
        RegisterContent(
            content = RegisterState.Content(
                name = ValidatedField(),
                email = ValidatedField(),
                password = ValidatedField(),
                confirmPassword = ValidatedField(),
                isRegisterButtonEnabled = false,
                isLoading = false
            ),
            onNameEntered = {},
            onEmailEntered = {},
            onPasswordEntered = {},
            onConfirmPasswordEntered = {},
            onRegisterButtonClicked = {},
            onCancelButtonClicked = {},
        )
    }
}