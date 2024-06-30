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



fun Screen.params(vararg  params: Any?): String {
    val routeParams = params.map { it.toString() }
    return this.name + "/" + routeParams.joinToString(separator = "/")
}


val EditPetRoute = "${Screen.EditPet.name}/${NavArg.petId.addBrackets()}"
val AddMedicineRoute = "${Screen.AddMedicine.name}/${NavArg.petId.addBrackets()}"
val EditMedicineRoute = "${Screen.EditMedicine.name}/${NavArg.medicineId.addBrackets()}"
val MedicineRoute = "${Screen.Medicine.name}/${NavArg.petId.addBrackets()}"

