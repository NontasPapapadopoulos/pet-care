package nondas.pap.petcareapp.infastracture.navigation

enum class NavArg(val param: String) {
    pet("pet"),
    medicine("medicine"),
}



fun NavArg.addBrackets(): String = "{$param}"