package nondas.pap.petcareapp.data.datasource

import nondas.pap.petcareapp.data.cache.dao.UserDao
import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.data.mapper.toNetwork
import nondas.pap.petcareapp.data.network.api.AuthApi
import nondas.pap.petcareapp.domain.entity.UserCredentials
import nondas.pap.petcareapp.domain.entity.UserDomainEntity

interface UserDataSource {

    suspend fun login(userCredentials: UserCredentials)

    suspend fun register(userDataEntity: UserDataEntity)

    suspend fun logout()

}


class UserDataSourceImpl(
    private val userDao: UserDao,
    private val authApi: AuthApi
): UserDataSource {
    override suspend fun login(userCredentials: UserCredentials) {
        TODO("Not yet implemented")
    }

    override suspend fun register(userDataEntity: UserDataEntity) {
        authApi.register(userDataEntity.toNetwork())

    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }


}