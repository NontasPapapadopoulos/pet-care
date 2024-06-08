package nondas.pap.petcareapp.data.mapper

import android.service.autofill.UserData
import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.data.network.entity.UserNetworkEntity
import nondas.pap.petcareapp.domain.entity.UserDomainEntity


fun UserDomainEntity.toData(isCurrentUser: Boolean): UserDataEntity = UserDataEntity(
    name = name,
    email = email,
    userId = userId,
    isCurrentUser = isCurrentUser
)



fun UserDataEntity.toDomain(): UserDomainEntity = UserDomainEntity(
    name = name,
    email = email,
    password = null
)


fun UserDomainEntity.toNetwork(): UserNetworkEntity = UserNetworkEntity(
    name = name,
    email = email,
)


fun UserDataEntity.toNetwork(): UserNetworkEntity = UserNetworkEntity(
    name = name,
    email = email,
)