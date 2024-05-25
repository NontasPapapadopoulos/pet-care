package nondas.pap.petcareapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "medicine")
data class MedicineDataEntity(
    @PrimaryKey(autoGenerate = true)
    val medicineId: Long,
    val type: String,
    val repeatRate: String,
    val dateReceived: String,
    val comments: String,
    val petId: Long
)
