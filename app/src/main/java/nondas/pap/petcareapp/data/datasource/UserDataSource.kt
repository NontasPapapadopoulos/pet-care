package nondas.pap.petcareapp.data.datasource


import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.cache.dao.PetDao
import nondas.pap.petcareapp.data.cache.dao.UserDao
import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.data.mapper.toData
import nondas.pap.petcareapp.data.mapper.toNetwork
import nondas.pap.petcareapp.data.network.PetCareApi
import nondas.pap.petcareapp.data.repository.DataStorageRepository
import nondas.pap.petcareapp.domain.entity.UserCredentials

interface UserDataSource {
    suspend fun login(userCredentials: UserCredentials)
    suspend fun register(userDataEntity: UserDataEntity)
    suspend fun logout()
    fun getUser(): Flow<UserDataEntity>

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

        println("user: $user")

        userDao.deselectUsers()
        userDao.put(user.toData().copy(isCurrentUser = true))


//        val pets = user.pets.map { it.toData() }
    //    petDao.addPets(pets)
        // store the user to dao


        // TODO: get the user details, store them in the local db. get the user`s pets and pet`s medicine's
    }

    override suspend fun register(userDataEntity: UserDataEntity) {
        petCareApi.register(userDataEntity.toNetwork())
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override fun getUser(): Flow<UserDataEntity> {
        return userDao.getCurrentUser()
    }




}

