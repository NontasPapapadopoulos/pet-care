package nondas.pap.petcareapp.data.repository

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.api.MedicineApi
import nondas.pap.petcareapp.domain.repository.MedicineRepository
import nondas.pap.petcareapp.domain.entity.Medicine
import retrofit2.Response

class MedicineRepositoryImpl(
    private val medicineApi: MedicineApi
): MedicineRepository {
    override fun getMedicine(petId: Long): Flow<List<Medicine>> {
        return medicineApi.getMedicine(petId = petId)
    }

    override suspend fun addMedicine(medicine: Medicine): Response<Unit> {
        return medicineApi.addMedicine(medicine)
    }

    override suspend fun editMedicine(medicine: Medicine): Response<Unit> {
        return medicineApi.editMedicine(medicine)
    }

    override suspend fun deleteMedicine(medicine: Medicine): Response<Unit> {
        return medicineApi.deleteMedicine(medicine)
    }
}