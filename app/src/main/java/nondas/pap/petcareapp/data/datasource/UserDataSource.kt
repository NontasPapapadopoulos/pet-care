package nondas.pap.petcareapp.data.datasource

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.cache.dao.UserDao
import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.data.mapper.toNetwork
import nondas.pap.petcareapp.data.network.api.AuthApi
import nondas.pap.petcareapp.data.network.api.UserApi
import nondas.pap.petcareapp.domain.entity.UserCredentials
import nondas.pap.petcareapp.domain.entity.UserDomainEntity

interface UserDataSource {
    suspend fun login(userCredentials: UserCredentials)
    suspend fun register(userDataEntity: UserDataEntity)
    suspend fun logout()
    fun getUser(): Flow<UserDataEntity>
}


class UserDataSourceImpl(
    private val userDao: UserDao,
    private val authApi: AuthApi,
): UserDataSource {
    override suspend fun login(userCredentials: UserCredentials) {
        authApi.login(userCredentials)
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