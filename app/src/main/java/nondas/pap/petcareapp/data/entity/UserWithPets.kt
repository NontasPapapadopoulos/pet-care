package nondas.pap.petcareapp.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithPets(
    @Embedded val user: UserDataEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val pets: List<PetDataEntity>
)