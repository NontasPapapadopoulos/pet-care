package nondas.pap.petcareapp.presentation.navigation.screen

const val MEDICINE_ROUTE = "medicine_route"

sealed class MedicineScreen(val route: String) {
    object Medicine: MedicineScreen(route = "medicine_screen")
    object AddMedicine: MedicineScreen(route = "add_medicine_screen")
    object EditMedicine: MedicineScreen(route = "edit_medicine_screen")

}
