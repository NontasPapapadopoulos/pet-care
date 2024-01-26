package nondas.pap.petcareapp.data.repository

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.network.api.MedicineApi
import nondas.pap.petcareapp.domain.repository.MedicineRepository
import nondas.pap.petcareapp.domain.entity.MedicineDomainEntity
import retrofit2.Response

class MedicineRepositoryImpl(
    private val medicineApi: MedicineApi
): MedicineRepository {
    override fun getMedicine(petId: Long): Flow<List<MedicineDomainEntity>> {
        return medicineApi.getMedicine(petId = petId)
    }

    override suspend fun addMedicine(medicineDomainEntity: MedicineDomainEntity): Response<Unit> {
        return medicineApi.addMedicine(medicineDomainEntity)
    }

    override suspend fun editMedicine(medicineDomainEntity: MedicineDomainEntity): Response<Unit> {
        return medicineApi.editMedicine(medicineDomainEntity)
    }

    override suspend fun deleteMedicine(medicineDomainEntity: MedicineDomainEntity): Response<Unit> {
        return medicineApi.deleteMedicine(medicineDomainEntity)
    }
}