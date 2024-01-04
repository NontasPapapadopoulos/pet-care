package nondas.pap.petcareapp.infastracture.navigation.screen

sealed class Screen(val route: String) {
    object Splash: Screen(route = "splash_screen")
    object Products: Screen(route = "login_screen")
    object Reports: Screen(route = "register_screen")




}
