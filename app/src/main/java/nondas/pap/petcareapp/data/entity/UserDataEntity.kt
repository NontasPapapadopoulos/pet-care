package nondas.pap.petcareapp.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import nondas.pap.petcareapp.presentation.navigation.PetScreen


@Entity(tableName = "user")
data class UserDataEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0,
    val name: String,
    val email: String,
    val isCurrentUser: Boolean,
//    val pets: List<PetDataEntity>
)
