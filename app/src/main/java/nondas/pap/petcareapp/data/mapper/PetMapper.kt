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
    userId = userId
)

fun PetDataEntity.toDomain(): PetDomainEntity = PetDomainEntity(
    name = name,
    kind = kind,
    age = age,
    userId = userId,
    petId = petId
)


fun PetDomainEntity.toData(): PetDataEntity = PetDataEntity(
    name = name,
    kind = kind,
    age = age,
    userId = userId,
    petId = petId
)


fun PetDataEntity.toNetwork(): PetNetworkEntity = PetNetworkEntity(
    name = name,
    kind = kind,
    age = age,
    id = petId,
    userId = userId
)
