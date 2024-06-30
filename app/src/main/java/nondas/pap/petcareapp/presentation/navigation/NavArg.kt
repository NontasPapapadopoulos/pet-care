package nondas.pap.petcareapp.presentation.navigation

enum class NavArg(val param: String) {
    petId("petId"),
    medicineId("medicineId"),
}



fun NavArg.addBrackets(): String = "{$param}"