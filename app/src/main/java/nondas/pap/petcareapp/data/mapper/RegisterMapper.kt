package nondas.pap.petcareapp.data.mapper

import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.data.network.entity.RegisterNetworkEntity



fun UserDataEntity.toNetwork() = RegisterNetworkEntity(
    email = email,
    password = password,
    name = name
)