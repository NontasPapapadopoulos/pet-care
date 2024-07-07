package nondas.pap.petcareapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserDataEntity(
    @PrimaryKey
    val userId: String = "0",
    val name: String,
    val email: String,
    val isCurrentUser: Boolean,
)