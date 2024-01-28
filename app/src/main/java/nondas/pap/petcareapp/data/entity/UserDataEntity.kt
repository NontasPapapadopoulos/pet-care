package nondas.pap.petcareapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserDataEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val email: String,
    val password: String
)
