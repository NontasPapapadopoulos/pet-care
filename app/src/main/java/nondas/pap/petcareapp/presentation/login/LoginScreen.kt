package nondas.pap.petcareapp.presentation.login

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
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest
import nondas.pap.petcareapp.infastracture.navigation.screen.PETS_ROUTE
import nondas.pap.petcareapp.infastracture.navigation.screen.Screen
import nondas.pap.petcareapp.presentation.UIEvent
import nondas.pap.petcareapp.presentation.component.VerticalSpace
import nondas.pap.petcareapp.presentation.component.ManageSystemBars

@OptIn(ExperimentalMaterial3Api::class)
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
        content = state,
        onEmailEntered = { viewModel.add(LoginEvent.EmailEntered(it)) },
        onPasswordEntered = { viewModel.add(LoginEvent.PasswordEntered(it)) },
        onLoginButtonClicked = { viewModel.add(LoginEvent.LoginButtonClicked(state.email, state.password)) },
        navController = navController
    )

}


@ExperimentalMaterial3Api
@Composable
private fun LoginContent(
    content: LoginState,
    onEmailEntered: (String) -> Unit,
    onPasswordEntered: (String) -> Unit,
    onLoginButtonClicked: () -> Unit,
    navController: NavController
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
            onClick = {
                navController.navigate(route = PETS_ROUTE)
                onLoginButtonClicked()
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Login")
        }

        VerticalSpace()

        Button(
            onClick = { navController.navigate(route = Screen.Reports.route) },
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
    LoginContent(
        content = LoginState(email = "", password = ""),
        onEmailEntered = {},
        onPasswordEntered = {},
        onLoginButtonClicked = {},
        navController = rememberNavController()
    )
}