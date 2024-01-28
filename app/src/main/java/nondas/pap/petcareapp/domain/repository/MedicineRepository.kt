package nondas.pap.petcareapp.domain.repository

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.domain.entity.MedicineDomainEntity
import retrofit2.Response

interface MedicineRepository {
fun getMedicine(petId: Int): Flow<List<MedicineDomainEntity>>

suspend fun addMedicine(medicineDomainEntity: MedicineDomainEntity)

suspend fun editMedicine(medicineDomainEntity: MedicineDomainEntity)

suspend fun deleteMedicine(medicineDomainEntity: MedicineDomainEntity)
}