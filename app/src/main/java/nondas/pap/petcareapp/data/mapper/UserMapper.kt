package nondas.pap.petcareapp.data.mapper

import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.data.network.entity.UserNetworkEntity
import nondas.pap.petcareapp.domain.entity.UserDomainEntity


//fun UserDomainEntity.toData(isCurrentUser: Boolean): UserDataEntity = UserDataEntity(
//    name = name,
//    email = email,
//    isCurrentUser = isCurrentUser,
//)


//fun UserDomainEntity.toNetwork(isCurrentUser: Boolean): UserNetworkEntity = UserNetworkEntity(
//    name = name,
//    email = email,
//    password = password,
//    userId = userId
//)


fun UserDataEntity.toDomain(): UserDomainEntity = UserDomainEntity(
    name = name,
    email = email,
    password = "",
    userId = userId.toString()

)



fun UserNetworkEntity.toData(): UserDataEntity = UserDataEntity(
    name = name,
    email = email,
    userId = userId.toLong(),
    isCurrentUser = false,
)

