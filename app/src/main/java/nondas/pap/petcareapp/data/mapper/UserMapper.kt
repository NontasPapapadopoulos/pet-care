package nondas.pap.petcareapp.data.mapper

import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.data.network.entity.UserNetworkEntity
import nondas.pap.petcareapp.domain.entity.UserDomainEntity


fun UserDomainEntity.toData(isCurrentUser: Boolean): UserDataEntity = UserDataEntity(
    name = name,
    email = email,
    isCurrentUser = isCurrentUser,
    password = password
)



fun UserDataEntity.toDomain(): UserDomainEntity = UserDomainEntity(
    name = name,
    email = email,
    password = password,
    userId = userId

)



fun UserNetworkEntity.toData(): UserDataEntity = UserDataEntity(
    name = name,
    email = email,
    isCurrentUser = false,
    password = password
)

