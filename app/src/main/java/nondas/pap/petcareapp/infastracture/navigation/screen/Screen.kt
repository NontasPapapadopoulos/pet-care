package nondas.pap.petcareapp.infastracture.navigation.screen

sealed class Screen(val route: String) {

    object Login: Screen(route = "login_screen")
    object Splash: Screen(route = "splash_screen")
    object Register: Screen(route = "register_screen")




}
