package nondas.pap.petcareapp.infastracture.navigation

enum class Screen {
    Login,
    Register,
    Main,
    EditPet,
    AddPet,
    Medicine,
    AddMedicine,
    EditMedicine
}


fun Screen.params(vararg  params: Any?): String {
    val routeParams = params.map { it.toString() }
    return this.name + "/" + routeParams.joinToString(separator = "/")
}