package nondas.pap.petcareapp.infastracture.navigation

sealed class Screen(val route: String) {

    object Login: Screen(route = "login_screen")
    object Home: Screen(route = "home_screen")
    object Splash: Screen(route = "splash_screen")
    object Register: Screen(route = "register_screen")
    object AddPet: Screen(route = "add_pet_screen")
    object EditPet: Screen(route = "edit_pet_screen")
    object AddMedicine: Screen(route = "add_medicine_screen")
    object EditMedicine: Screen(route = "edit_medicine_screen")



}
