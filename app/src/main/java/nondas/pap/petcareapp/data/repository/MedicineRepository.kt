package nondas.pap.petcareapp.data.repository

import nondas.pap.petcareapp.domain.model.Medicine
import nondas.pap.petcareapp.domain.model.Pet
import retrofit2.Response

interface MedicineRepository {
suspend fun getMedicine(petId: Long): Response<List<Medicine>>

suspend fun addMedicine(medicine: Medicine): Response<Unit>

suspend fun editMedicine(medicine: Medicine): Response<Unit>

suspend fun deleteMedicine(medicine: Medicine): Response<Unit>
}