package nondas.pap.petcareapp.data.datasource


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import nondas.pap.petcareapp.data.cache.dao.PetDao
import nondas.pap.petcareapp.data.cache.dao.UserDao
import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.data.mapper.toData
import nondas.pap.petcareapp.data.mapper.toNetwork
import nondas.pap.petcareapp.data.network.PetCareApi
import nondas.pap.petcareapp.data.repository.DataStorageRepository
import nondas.pap.petcareapp.domain.entity.UserCredentials
import nondas.pap.petcareapp.domain.entity.UserDomainEntity

interface UserDataSource {
    suspend fun login(userCredentials: UserCredentials)
    suspend fun register(userDomainEntity: UserDomainEntity)
    suspend fun logout()
    fun getUserFlow(): Flow<UserDataEntity>
    suspend fun getUser(): UserDataEntity

}



class UserDataSourceImpl(
    private val userDao: UserDao,
    private val petDao: PetDao,
    private val petCareApi: PetCareApi,
    private val dataStorage: DataStorageRepository

): UserDataSource {
    override suspend fun login(userCredentials: UserCredentials) {

        val token = petCareApi.login(userCredentials).token
        dataStorage.saveToken(token)
        val user = petCareApi.getUser(userCredentials.email)

        // find a way to deal with Api Response

        userDao.deselectUsers()
        userDao.put(user.toData().copy(isCurrentUser = true))


//        val pets = user.pets.map { it.toData() }
    //    petDao.addPets(pets)
        // store the user to dao


        // TODO: get the user details, store them in the local db. get the user`s pets and pet`s medicine's
    }

    override suspend fun register(userDomainEntity: UserDomainEntity) {
        petCareApi.register(userDomainEntity.toNetwork())
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override fun getUserFlow(): Flow<UserDataEntity> {
        return userDao.getCurrentUserFlow()
    }

    override suspend fun getUser(): UserDataEntity {
        return userDao.getCurrentUser()

    }

}

