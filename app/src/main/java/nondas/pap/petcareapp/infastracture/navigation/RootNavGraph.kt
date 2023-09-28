package nondas.pap.petcareapp.infastracture.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import nondas.pap.petcareapp.presentation.RegisterScreen
import nondas.pap.petcareapp.presentation.SplashScreen
import nondas.pap.petcareapp.presentation.login.LoginScreen


const val ROOT_GRAPH_ROUTE = "root"


@Composable
fun RootNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        route = ROOT_GRAPH_ROUTE,
        startDestination = Screen.SplashScreen.route
    ) {



        composable(
            route = Screen.SplashScreen.route
        ) {

            displaySplashScreen(
                navController = navController,
                milliseconds = 1200L,
                route = Screen.LoginScreen.route
            )

            SplashScreen(navController = navController)

        }

        composable(
            route = Screen.LoginScreen.route
        ) {

            LoginScreen(navController = navController)
        }

        composable(
            route = Screen.RegisterScreen.route
        ) {

            RegisterScreen(navController = navController)
        }


    }
}



@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}


@Composable
fun displaySplashScreen(
    navController: NavController,
    milliseconds: Long,
    route: String
) {
    LaunchedEffect(Unit) {
        delay(milliseconds)
        navController.navigate(route = route)
    }

}