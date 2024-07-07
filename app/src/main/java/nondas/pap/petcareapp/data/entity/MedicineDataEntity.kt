package nondas.pap.petcareapp.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey





@Entity(
    tableName = "medicine",
    foreignKeys = [ForeignKey(
        entity = PetDataEntity::class,
        parentColumns = ["petId"],
        childColumns = ["petId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["petId"])]
)
data class MedicineDataEntity(
    @PrimaryKey
    val medicineId: String,
    val type: String,
    val repeatRate: String,
    val dateReceived: String,
    val comments: String,
    val petId: String
)
