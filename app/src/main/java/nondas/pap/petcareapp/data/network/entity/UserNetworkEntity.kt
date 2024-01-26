package nondas.pap.petcareapp.data.network.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserNetworkEntity(
    @PrimaryKey
    val id: String
)
