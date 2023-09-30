package nondas.pap.petcareapp.domain.model

data class Pet(
    val name: String,
    val kind: String,
    val breed: String,
    val age: Int,
    val medicines: List<Medicine> = listOf()

)
