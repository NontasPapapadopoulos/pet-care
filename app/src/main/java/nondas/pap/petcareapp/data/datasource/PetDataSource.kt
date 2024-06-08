package nondas.pap.petcareapp.data.datasource

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.cache.dao.PetDao
import nondas.pap.petcareapp.data.entity.PetDataEntity
import nondas.pap.petcareapp.data.network.api.PetCareApi
import javax.inject.Inject

interface PetDataSource {

    fun getPets(userId: String): Flow<List<PetDataEntity>>

    suspend fun addPet(pet: PetDataEntity)

    suspend fun updatePet(pet: PetDataEntity)

    suspend fun deletePet(pet: PetDataEntity)

}

class PetDataSourceImpl @Inject constructor(
    private val petDao: PetDao,
    private val petCareApi: PetCareApi
): PetDataSource {

    override fun getPets(userId: String): Flow<List<PetDataEntity>> {
        return petDao.getPets(userId)
    }

    override suspend fun addPet(pet: PetDataEntity) {
        petDao.addPet(pet)
    }

    override suspend fun updatePet(pet: PetDataEntity) {
        petDao.updatePet(pet)
    }

    override suspend fun deletePet(pet: PetDataEntity) {
        petDao.deletePet(pet)
    }

}