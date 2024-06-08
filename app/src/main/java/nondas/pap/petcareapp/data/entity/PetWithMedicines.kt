package nondas.pap.petcareapp.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PetWithMedicines(
    @Embedded val pet: PetDataEntity,
    @Relation(
        parentColumn = "petId",
        entityColumn = "petId"
    )
    val medicines: List<MedicineDataEntity>
)
