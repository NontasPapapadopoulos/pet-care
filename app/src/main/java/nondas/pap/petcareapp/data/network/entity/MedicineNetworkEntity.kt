package nondas.pap.petcareapp.data.network.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class MedicineNetworkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val type: String,
    val repeatRate: String,
    val dateReceived: String,
    val comments: String,
    val petId: Long
)
