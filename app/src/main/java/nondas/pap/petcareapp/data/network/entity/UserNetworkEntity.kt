package nondas.pap.petcareapp.data.network.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


data class UserNetworkEntity(
    val email: String,
    val password: String,
    val name: String,
    val userId: Int = 0
)
