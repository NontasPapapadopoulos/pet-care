package nondas.pap.petcareapp.data.datasource

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.cache.dao.MedicineDao
import nondas.pap.petcareapp.data.entity.MedicineDataEntity
import nondas.pap.petcareapp.data.network.api.MedicineApi
import nondas.pap.petcareapp.domain.entity.MedicineDomainEntity
import javax.inject.Inject

interface MedicineDataSource {

    fun fetchMedicines(): Flow<List<MedicineDataEntity>>

    fun saveMedicine(medicineDomainEntity: MedicineDomainEntity)

    fun editMedicine(medicineDomainEntity: MedicineDomainEntity)

    fun deleteMedicine(medicineDomainEntity: MedicineDomainEntity)
}


class MedicineDataSourceImpl @Inject constructor(
    private val medicineDao: MedicineDao,
    private val medicineApi: MedicineApi
): MedicineDataSource {
    override fun fetchMedicines(): Flow<List<MedicineDataEntity>> {
        TODO("Not yet implemented")
    }

    override fun saveMedicine(medicineDomainEntity: MedicineDomainEntity) {
        TODO("Not yet implemented")
    }

    override fun editMedicine(medicineDomainEntity: MedicineDomainEntity) {
        TODO("Not yet implemented")
    }

    override fun deleteMedicine(medicineDomainEntity: MedicineDomainEntity) {
        TODO("Not yet implemented")
    }

}