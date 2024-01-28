package nondas.pap.petcareapp.data.mapper

import android.service.autofill.UserData
import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.domain.entity.UserDomainEntity


fun UserDomainEntity.toData(): UserDataEntity = UserDataEntity(
    name = name,
    email = email,
    password = password,
    id = userId
)