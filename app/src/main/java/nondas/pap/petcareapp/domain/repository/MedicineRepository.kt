package nondas.pap.petcareapp.domain.repository

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.domain.entity.MedicineDomainEntity
import retrofit2.Response

interface MedicineRepository {
fun getMedicine(petId: Long): Flow<List<MedicineDomainEntity>>

suspend fun addMedicine(medicineDomainEntity: MedicineDomainEntity): Response<Unit>

suspend fun editMedicine(medicineDomainEntity: MedicineDomainEntity): Response<Unit>

suspend fun deleteMedicine(medicineDomainEntity: MedicineDomainEntity): Response<Unit>
}