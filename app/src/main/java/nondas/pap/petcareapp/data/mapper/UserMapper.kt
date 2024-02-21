package nondas.pap.petcareapp.data.mapper

import android.service.autofill.UserData
import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.data.network.entity.UserNetworkEntity
import nondas.pap.petcareapp.domain.entity.UserDomainEntity


fun UserDomainEntity.toData(): UserDataEntity = UserDataEntity(
    name = name,
    email = email,
    password = password,
    id = userId
)



fun UserDataEntity.toDomain(): UserDomainEntity = UserDomainEntity(
    name = name,
    email = email,
    password = password,
)


fun UserDomainEntity.toNetwork(): UserNetworkEntity = UserNetworkEntity(
    name = name,
    email = email,
    password = password,
)


fun UserDataEntity.toNetwork(): UserNetworkEntity = UserNetworkEntity(
    name = name,
    email = email,
    password = password,
)