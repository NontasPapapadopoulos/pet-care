package nondas.pap.petcareapp.data.cache

import androidx.room.Embedded
import androidx.room.Relation
import nondas.pap.petcareapp.data.entity.MedicineDataEntity
import nondas.pap.petcareapp.data.entity.PetDataEntity

data class PetWithMedicines(
    @Embedded val pet: PetDataEntity,
    @Relation(
        parentColumn = "petId",
        entityColumn = "medicineId"
    )
    val medicines: List<MedicineDataEntity>
)
