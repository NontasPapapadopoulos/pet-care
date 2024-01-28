package nondas.pap.petcareapp.data.repository

import nondas.pap.petcareapp.domain.repository.PetRepository
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import retrofit2.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import nondas.pap.petcareapp.data.datasource.PetDataSource
import nondas.pap.petcareapp.data.mapper.toData
import nondas.pap.petcareapp.data.mapper.toDomain

class PetRepositoryImpl(
    private val petDataSource: PetDataSource
): PetRepository {
    override fun getPets(userId: Int): Flow<List<PetDomainEntity>> {
        return petDataSource.fetchPets(userId = userId).map { pet -> pet.map { it.toDomain() } }
    }

    override suspend fun addPet(petDomainEntity: PetDomainEntity) {
        return petDataSource.addPet(petDomainEntity.toData())
    }

    override suspend fun editPet(petDomainEntity: PetDomainEntity) {
        return petDataSource.updatePet(petDomainEntity.toData())
    }

    override suspend fun deletePet(petDomainEntity: PetDomainEntity) {
        return petDataSource.deletePet(petDomainEntity.toData())
    }

}