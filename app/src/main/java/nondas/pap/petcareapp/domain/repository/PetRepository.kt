package nondas.pap.petcareapp.domain.repository

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import retrofit2.Response

interface PetRepository {
    fun getPets(userId: String): Flow<List<PetDomainEntity>>

    suspend fun addPet(petDomainEntity: PetDomainEntity)

    suspend fun editPet(petDomainEntity: PetDomainEntity)

    suspend fun deletePet(petDomainEntity: PetDomainEntity)

}