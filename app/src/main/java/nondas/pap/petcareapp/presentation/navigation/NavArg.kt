package nondas.pap.petcareapp.presentation.navigation

enum class NavArg(val param: String) {
    pet("pet"),
    medicine("medicine"),
}



fun NavArg.addBrackets(): String = "{$param}"