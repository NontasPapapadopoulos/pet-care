package nondas.pap.petcareapp.data.datasource

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.cache.dao.PetDao
import nondas.pap.petcareapp.data.entity.PetDataEntity
import nondas.pap.petcareapp.data.network.api.PetApi
import javax.inject.Inject

interface PetDataSource {

    fun fetchPets(): Flow<List<PetDataEntity>>

    fun savePet(pet: PetDataEntity)

    fun editPet(pet: PetDataEntity)

    fun deletePet(pet: PetDataEntity)

}

class PetDataSourceImpl @Inject constructor(
    private val petDao: PetDao,
    private val petApi: PetApi
): PetDataSource {

    override fun fetchPets(): Flow<List<PetDataEntity>> {
        TODO("Not yet implemented")
    }

    override fun savePet(pet: PetDataEntity) {
        TODO("Not yet implemented")
    }

    override fun editPet(pet: PetDataEntity) {
        TODO("Not yet implemented")
    }

    override fun deletePet(pet: PetDataEntity) {
        TODO("Not yet implemented")
    }

}