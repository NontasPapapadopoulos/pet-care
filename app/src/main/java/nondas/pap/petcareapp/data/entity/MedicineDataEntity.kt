package nondas.pap.petcareapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date



@Entity
data class MedicineDataEntity(
    @PrimaryKey(autoGenerate = true)
    val medicineId: Int,
    val type: String,
    val repeatRate: String,
    val dateReceived: String,
    val comments: String
)
