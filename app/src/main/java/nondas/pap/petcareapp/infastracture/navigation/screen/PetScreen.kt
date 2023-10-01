package nondas.pap.petcareapp.infastracture.navigation.screen

const val PETS_ROUTE = "pets_route"



sealed class PetScreen(val route: String) {

    object Pets: PetScreen(route = "pets_screen")
    object AddPet: PetScreen(route = "add_pet_screen")
    object EditPet: PetScreen(route = "edit_pet_screen")

}
