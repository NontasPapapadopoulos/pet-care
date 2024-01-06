package nondas.pap.petcareapp.domain.repository

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.domain.entity.Medicine
import retrofit2.Response

interface MedicineRepository {
fun getMedicine(petId: Long): Flow<List<Medicine>>

suspend fun addMedicine(medicine: Medicine): Response<Unit>

suspend fun editMedicine(medicine: Medicine): Response<Unit>

suspend fun deleteMedicine(medicine: Medicine): Response<Unit>
}