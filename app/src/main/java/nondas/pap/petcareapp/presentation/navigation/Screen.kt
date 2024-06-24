package nondas.pap.petcareapp.presentation.navigation

enum class Screen {
    Splash,
    Login,
    Register,
    Main,
    EditPet,
    AddPet,
    Medicine,
    AddMedicine,
    EditMedicine
}



const val PETS_ROUTE = "pets_route"

enum class Route {
    Pet,
    Medicine
}

sealed class PetScreen(val route: String) {

    object Pets: PetScreen(route = "pets_screen")
    object AddPet: PetScreen(route = "add_pet_screen")
    object EditPet: PetScreen(route = "edit_pet_screen")

}



fun Screen.params(vararg  params: Any?): String {
    val routeParams = params.map { it.toString() }
    return this.name + "/" + routeParams.joinToString(separator = "/")
}