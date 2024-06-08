package nondas.pap.petcareapp.data.datasource


import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.cache.dao.PetDao
import nondas.pap.petcareapp.data.cache.dao.UserDao
import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.data.mapper.toData
import nondas.pap.petcareapp.data.mapper.toNetwork
import nondas.pap.petcareapp.data.network.api.AuthApi
import nondas.pap.petcareapp.data.network.api.PetCareApi
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
    private val authApi: AuthApi,
    private val petCareApi: PetCareApi,
    private val dataStorage: DataStorageRepository

): UserDataSource {
    override suspend fun login(userCredentials: UserCredentials) {

        val token = authApi.login(userCredentials)
//        dataStorage.saveToken(token)
        val user = petCareApi.getUser(userCredentials.email)
        println("user        " + user)
//        val userDataEntity = UserDataEntity(
//            name = user.name,
//            email = user.email,
//            isCurrentUser = true
//        )
//        userDao.put(userDataEntity)
//
//        val pets = user.pets.map { it.toData() }
    //    petDao.addPets(pets)
        // store the user to dao
        // TODO: get the user details, store them in the local db. get the user`s pets and pet`s medicine's
    }

    override suspend fun register(userDataEntity: UserDataEntity) {
        authApi.register(userDataEntity.toNetwork())
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override fun getUser(): Flow<UserDataEntity> {
        return userDao.getCurrentUser()
    }




}

