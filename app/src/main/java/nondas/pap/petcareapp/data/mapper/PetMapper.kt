package nondas.pap.petcareapp.data.mapper

import nondas.pap.petcareapp.data.entity.PetDataEntity
import nondas.pap.petcareapp.data.network.entity.PetNetworkEntity
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import okhttp3.internal.userAgent


fun PetNetworkEntity.toData(): PetDataEntity = PetDataEntity(
    petId = id,
    name = name,
    kind = kind,
    age = age,
    userId = userId,
    dob = dob
)

fun PetDataEntity.toDomain(): PetDomainEntity = PetDomainEntity(
    name = name,
    kind = kind,
    age = age,
    userId = userId,
    petId = petId,
    dob = dob

)


fun PetDomainEntity.toData(): PetDataEntity = PetDataEntity(
    name = name,
    kind = kind,
    age = age,
    userId = userId,
    petId = petId,
    dob = dob
)


fun PetDataEntity.toNetwork(): PetNetworkEntity = PetNetworkEntity(
    name = name,
    kind = kind,
    age = age,
    id = petId,
    userId = userId,
    dob = dob
)
