package nondas.pap.petcareapp.data.repository

import nondas.pap.petcareapp.data.network.api.AuthApi
import nondas.pap.petcareapp.domain.entity.UserCredentials
import nondas.pap.petcareapp.domain.repository.AuthenticateRepository
import nondas.pap.petcareapp.domain.entity.UserDomainEntity

class AuthenticateRepositoryImpl(
    private val authApi: AuthApi
): AuthenticateRepository {
    override suspend fun login(userCredentials: UserCredentials) {
        authApi.login(userCredentials)
    }

    override suspend fun register(userDomainEntity: UserDomainEntity) {
       authApi.register(userDomainEntity)
    }


}