package nondas.pap.petcareapp.data.mapper

import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.data.network.entity.UserNetworkEntity
import nondas.pap.petcareapp.domain.entity.UserDomainEntity


fun UserDomainEntity.toData(isCurrentUser: Boolean): UserDataEntity = UserDataEntity(
    name = name,
    email = email,
    isCurrentUser = isCurrentUser,
)


fun UserDomainEntity.toNetwork(isCurrentUser: Boolean): UserNetworkEntity = UserNetworkEntity(
    name = name,
    email = email,
    password = password
)


fun UserDataEntity.toDomain(): UserDomainEntity = UserDomainEntity(
    name = name,
    email = email,
    password = "",
)



fun UserNetworkEntity.toData(): UserDataEntity = UserDataEntity(
    name = name,
    email = email,
    isCurrentUser = false,
)

