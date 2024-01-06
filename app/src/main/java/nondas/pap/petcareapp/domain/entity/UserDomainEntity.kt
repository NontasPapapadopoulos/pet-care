package nondas.pap.petcareapp.domain.entity

data class UserDomainEntity(
    val email: String,
    val password: String,
    val name: String,
    val pets: List<PetDomainEntity>? = listOf()
)
