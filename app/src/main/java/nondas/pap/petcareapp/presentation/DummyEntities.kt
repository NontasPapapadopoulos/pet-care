package nondas.pap.petcareapp.presentation

import nondas.pap.petcareapp.domain.entity.UserDomainEntity
import java.util.UUID


object DummyEntities

val DummyEntities.user: UserDomainEntity
    get() = UserDomainEntity(
        email = "papadopoulose92@gmail.com",
        password = "Aa123!",
        name = "Nondas",
        userId =  UUID.randomUUID().toString()
    )