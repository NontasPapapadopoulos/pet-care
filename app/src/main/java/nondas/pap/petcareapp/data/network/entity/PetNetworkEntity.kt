package nondas.pap.petcareapp.data.network.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PetNetworkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val kind: String,
    val age: Int,
    val userId: Int
)
