package nondas.pap.petcareapp.data.repository

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.domain.model.Medicine
import nondas.pap.petcareapp.domain.model.Pet
import retrofit2.Response

interface MedicineRepository {
fun getMedicine(petId: Long): Flow<List<Medicine>>

suspend fun addMedicine(medicine: Medicine): Response<Unit>

suspend fun editMedicine(medicine: Medicine): Response<Unit>

suspend fun deleteMedicine(medicine: Medicine): Response<Unit>
}