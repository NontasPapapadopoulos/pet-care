package nondas.pap.petcareapp.data.network.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PetNetworkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val name: String,
    val kind: String,
    val age: String,
    val userId: String,
    val dob: String
)
