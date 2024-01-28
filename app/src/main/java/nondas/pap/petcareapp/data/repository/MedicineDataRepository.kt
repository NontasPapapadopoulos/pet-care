package nondas.pap.petcareapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nondas.pap.petcareapp.data.datasource.MedicineDataSource
import nondas.pap.petcareapp.data.mapper.toData
import nondas.pap.petcareapp.data.mapper.toDomain
import nondas.pap.petcareapp.domain.repository.MedicineRepository
import nondas.pap.petcareapp.domain.entity.MedicineDomainEntity
import retrofit2.Response

class MedicineDataRepository(
    private val medicineDataSource: MedicineDataSource
): MedicineRepository {
    override fun getMedicine(petId: Int): Flow<List<MedicineDomainEntity>> {
        return medicineDataSource.fetchMedicines(petId = petId).map { med -> med.map { it.toDomain() } }
    }

    override suspend fun addMedicine(medicineDomainEntity: MedicineDomainEntity) {
        return medicineDataSource.addMedicine(medicineDomainEntity.toData())
    }

    override suspend fun editMedicine(medicineDomainEntity: MedicineDomainEntity){
        return medicineDataSource.updateMedicine(medicineDomainEntity.toData())
    }

    override suspend fun deleteMedicine(medicineDomainEntity: MedicineDomainEntity){
        return medicineDataSource.deleteMedicine(medicineDomainEntity.toData())
    }
}