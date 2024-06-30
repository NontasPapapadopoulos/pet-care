package nondas.pap.petcareapp.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key

import androidx.compose.material.icons.filled.Pets

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import kotlinx.coroutines.flow.collectLatest
import nondas.pap.petcareapp.presentation.component.VerticalSpace
import nondas.pap.petcareapp.presentation.component.ManageSystemBars
import nondas.pap.petcareapp.ui.theme.PetCareAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToPetsRoute: () -> Unit,
    onNavigateToRegister: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
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

    LaunchedEffect(Unit) {
        viewModel.navigationFlow.collectLatest { navigate ->
            if (navigate)
                onNavigateToPetsRoute()
        }
    }

    ManageSystemBars()

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LoginContent(
        content = state,
        onEmailEntered = { viewModel.add(LoginEvent.EmailEntered(it)) },
        onPasswordEntered = { viewModel.add(LoginEvent.PasswordEntered(it)) },
        onLoginButtonClicked = { viewModel.add(LoginEvent.LoginButtonClicked(state.email, state.password)) },
        onNavigateToRegister = onNavigateToRegister
    )

}


@ExperimentalMaterial3Api
@Composable
private fun LoginContent(
    content: LoginState,
    onEmailEntered: (String) -> Unit,
    onPasswordEntered: (String) -> Unit,
    onLoginButtonClicked: () -> Unit,
    onNavigateToRegister: () -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 50.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = "Pet Care",
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
            value = content.email,
            onValueChange = { onEmailEntered(it) },
            label = { Text(text = "Email") },
            trailingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = null,
                )
             },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = content.password,
            onValueChange = { onPasswordEntered(it) },
            label = { Text(text = "Password") },
            trailingIcon = {
                Icon(
                    Icons.Default.Key,
                    contentDescription = null,
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )


        VerticalSpace(20)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Forgot password?",
                style =  MaterialTheme.typography.bodySmall,
                modifier = Modifier.clickable {  }
            )
        }


        VerticalSpace(20)

        Spacer(modifier = Modifier.weight(1f))


        Button(
            onClick = { onLoginButtonClicked() },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Login")
        }

        VerticalSpace()

        Button(
            onClick = { onNavigateToRegister() },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Register")
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun LoginContentPreview() {
    PetCareAppTheme {
        LoginContent(
            content = LoginState(email = "", password = ""),
            onEmailEntered = {},
            onPasswordEntered = {},
            onLoginButtonClicked = {},
            onNavigateToRegister = {}
        )
    }
}