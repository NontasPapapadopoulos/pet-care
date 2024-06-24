package nondas.pap.petcareapp.presentation.navigation.graph


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.delay
import nondas.pap.petcareapp.presentation.navigation.Route
import nondas.pap.petcareapp.presentation.navigation.Screen

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
        startDestination = Screen.Splash.name
    ) {



        composable(
            route = Screen.Splash.name
        ) {

            DisplaySplashScreen(
                navController = navController,
                milliseconds = 200L,
                route = Screen.Login.name
            )

            SplashScreen()

        }

        composable(
            route = Screen.Login.name
        ) {

            LoginScreen(
                onNavigateToPetsRoute = { navController.navigate(route = Route.Pet.name) },
                onNavigateToRegister = { navController.navigate(route = Screen.Register.name) }
            )
        }

        composable(
            route = Screen.Register.name
        ) {

            RegisterScreen(onNavigateBack = { navController.popBackStack() })
        }

        petsNavGraph(navController)
        medicineNavGraph(navController)

    }
}





@Composable
fun DisplaySplashScreen(
    navController: NavController,
    milliseconds: Long,
    route: String
) {
    LaunchedEffect(Unit) {
        delay(milliseconds)
        navController.navigate(route = route)
    }

}