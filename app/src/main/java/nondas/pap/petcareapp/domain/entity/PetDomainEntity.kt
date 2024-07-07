package nondas.pap.petcareapp.domain.entity

data class PetDomainEntity(
    val name: String = "",
    val kind: String = "",
    val age: String = "0",
    val medicineDomainEntities: List<MedicineDomainEntity> = listOf(),
    val userId: Long = 0,
    val petId: Long = 0,

)
