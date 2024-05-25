package nondas.pap.petcareapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserDataEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val isCurrentUser: Boolean
)
