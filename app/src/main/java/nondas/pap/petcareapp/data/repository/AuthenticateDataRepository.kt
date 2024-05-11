package nondas.pap.petcareapp.data.repository

import nondas.pap.petcareapp.data.datasource.UserDataSource
import nondas.pap.petcareapp.data.mapper.toData
import nondas.pap.petcareapp.data.network.api.AuthApi
import nondas.pap.petcareapp.domain.entity.UserCredentials
import nondas.pap.petcareapp.domain.repository.AuthenticateRepository
import nondas.pap.petcareapp.domain.entity.UserDomainEntity

class AuthenticateDataRepository(
    private val userDataSource: UserDataSource
): AuthenticateRepository {
    override suspend fun login(userCredentials: UserCredentials) {
        userDataSource.login(userCredentials)
    }

    override suspend fun register(userDomainEntity: UserDomainEntity) {
        userDataSource.register(userDomainEntity.toData(false))
    }


}