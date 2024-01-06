package nondas.pap.petcareapp.domain.repository

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import retrofit2.Response

interface PetRepository {
    fun getPets(userId: Long): Flow<List<PetDomainEntity>>

    suspend fun addPet(petDomainEntity: PetDomainEntity): Response<Unit>

    suspend fun editPet(petDomainEntity: PetDomainEntity): Response<Unit>

    suspend fun deletePet(petDomainEntity: PetDomainEntity): Response<Unit>

}