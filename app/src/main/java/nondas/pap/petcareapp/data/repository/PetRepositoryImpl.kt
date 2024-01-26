package nondas.pap.petcareapp.data.repository

import nondas.pap.petcareapp.data.network.api.PetApi
import nondas.pap.petcareapp.domain.repository.PetRepository
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import retrofit2.Response
import kotlinx.coroutines.flow.Flow

class PetRepositoryImpl(
    private val petApi: PetApi
): PetRepository {
    override fun getPets(userId: Long): Flow<List<PetDomainEntity>> {
        return petApi.getPets(userId = userId)
    }

    override suspend fun addPet(petDomainEntity: PetDomainEntity): Response<Unit> {
        return petApi.addPet(petDomainEntity)
    }

    override suspend fun editPet(petDomainEntity: PetDomainEntity): Response<Unit> {
        return petApi.editPet(petDomainEntity)
    }

    override suspend fun deletePet(petDomainEntity: PetDomainEntity): Response<Unit> {
        return petApi.deletePet(petDomainEntity)
    }

}