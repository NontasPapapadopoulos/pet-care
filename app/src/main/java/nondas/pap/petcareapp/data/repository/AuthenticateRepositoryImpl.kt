package nondas.pap.petcareapp.data.repository

import nondas.pap.petcareapp.data.api.AuthApi
import nondas.pap.petcareapp.domain.repository.AuthenticateRepository
import nondas.pap.petcareapp.domain.model.Pet
import nondas.pap.petcareapp.domain.model.UserCredentials
import nondas.pap.petcareapp.domain.model.UserDetails

class AuthenticateRepositoryImpl(
    private val authApi: AuthApi
): AuthenticateRepository {
    override suspend fun login(userCredentials: UserCredentials) {
        authApi.login(userCredentials)
    }

    override suspend fun register(userDetails: UserDetails) {
       authApi.register(userDetails)
    }


}