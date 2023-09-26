package nondas.pap.petcareapp.infastracture.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable



const val ROOT_GRAPH_ROUTE = "root"


@Composable
fun RootNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        route = ROOT_GRAPH_ROUTE,
        startDestination = Screen.LoginScreen.route
        ) {

        composable(
            route = Screen.LoginScreen.route
        ) {

           // LoginScreen(navController = navController)

        }


        //mainNavGraph(navController = navController)


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

//composable(
//route = FlykkScreen.FlykkSplashScreen.route
//) {
//
//    displaySplashScreen(
//        navController = navController,
//        milliseconds = 1000L,
//        route = AuthScreen.LoginAuthScreen.route
//    )
//
//    FlykkSplashScreen(
//        navigate = {
//            navController.navigate(AuthScreen.LoginAuthScreen.route)
//        }
//    )
//}