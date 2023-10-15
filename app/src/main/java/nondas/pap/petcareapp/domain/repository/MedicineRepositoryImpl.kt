package nondas.pap.petcareapp.domain.repository

import nondas.pap.petcareapp.data.api.MedicineApi
import nondas.pap.petcareapp.data.repository.MedicineRepository
import nondas.pap.petcareapp.domain.model.Medicine
import retrofit2.Response

class MedicineRepositoryImpl(
    private val medicineApi: MedicineApi
): MedicineRepository {
    override suspend fun getMedicine(petId: Long): Response<List<Medicine>> {
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