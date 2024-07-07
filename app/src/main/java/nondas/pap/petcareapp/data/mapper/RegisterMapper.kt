package nondas.pap.petcareapp.data.mapper

import nondas.pap.petcareapp.data.network.entity.RegisterNetworkEntity
import nondas.pap.petcareapp.domain.entity.UserDomainEntity


fun UserDomainEntity.toNetwork() = RegisterNetworkEntity(
    email = email,
    password = password,
    name = name
)