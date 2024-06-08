package nondas.pap.petcareapp.data.datasource

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.cache.dao.MedicineDao
import nondas.pap.petcareapp.data.entity.MedicineDataEntity
import nondas.pap.petcareapp.data.network.api.PetCareApi
import javax.inject.Inject

interface MedicineDataSource {
    fun fetchMedicines(petId: Int): Flow<List<MedicineDataEntity>>

    suspend fun addMedicine(medicineDataEntity: MedicineDataEntity)

    suspend fun updateMedicine(medicineDataEntity: MedicineDataEntity)

    suspend fun deleteMedicine(medicineDataEntity: MedicineDataEntity)
}


class MedicineDataSourceImpl @Inject constructor(
    private val medicineDao: MedicineDao,
    private val petCareApi: PetCareApi
): MedicineDataSource {
    override fun fetchMedicines(petId: Int): Flow<List<MedicineDataEntity>> {
        return medicineDao.getPetMedicines(petId)
    }

    override suspend fun addMedicine(medicineDataEntity: MedicineDataEntity) {
        medicineDao.addMedicine(medicineDataEntity)
    }

    override suspend fun updateMedicine(medicineDataEntity: MedicineDataEntity) {
        medicineDao.updateMedicine(medicineDataEntity)
    }

    override suspend fun deleteMedicine(medicineDataEntity: MedicineDataEntity) {
        medicineDao.deleteMedicine(medicineDataEntity)
    }

}