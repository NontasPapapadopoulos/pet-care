package nondas.pap.petcareapp.domain.entity

data class PetDomainEntity(
    val name: String = "",
    val kind: String = "",
    val age: Int = 0,
    val medicineDomainEntities: List<MedicineDomainEntity> = listOf(),
    val userId: Int = 0,
    val petId: Int= 0

)
