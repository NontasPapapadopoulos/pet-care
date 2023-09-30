package nondas.pap.petcareapp.domain.model

data class UserDetails(
    val name: String,
    val email: String,
    val password: String,
    val pets: List<Pet>
)
