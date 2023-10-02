package nondas.pap.petcareapp.infastracture.navigation.graph


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.delay
import nondas.pap.petcareapp.infastracture.navigation.graph.medicineNavGraph
import nondas.pap.petcareapp.infastracture.navigation.graph.petsNavGraph
import nondas.pap.petcareapp.infastracture.navigation.screen.Screen

import nondas.pap.petcareapp.presentation.SplashScreen
import nondas.pap.petcareapp.presentation.login.LoginScreen
import nondas.pap.petcareapp.presentation.register.RegisterScreen


const val ROOT_GRAPH_ROUTE = "root"


@Composable
fun RootNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        route = ROOT_GRAPH_ROUTE,
        startDestination = Screen.Splash.route
    ) {



        composable(
            route = Screen.Splash.route
        ) {

            displaySplashScreen(
                navController = navController,
                milliseconds = 1200L,
                route = Screen.Login.route
            )

            SplashScreen(navController = navController)

        }

        composable(
            route = Screen.Login.route
        ) {

            LoginScreen(navController = navController)
        }

        composable(
            route = Screen.Register.route
        ) {

            RegisterScreen(navController = navController)
        }

        petsNavGraph(navController)
        medicineNavGraph(navController)

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