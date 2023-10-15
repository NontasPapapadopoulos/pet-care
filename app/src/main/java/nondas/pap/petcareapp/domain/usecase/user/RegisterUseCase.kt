package nondas.pap.petcareapp.domain.usecase.user

import nondas.pap.petcareapp.domain.model.UserDetails
import nondas.pap.petcareapp.domain.repository.AuthenticateRepositoryImpl
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authenticateRepositoryImpl: AuthenticateRepositoryImpl
) {

    suspend fun execute(userDetails: UserDetails) {

        authenticateRepositoryImpl.register(userDetails)

    }
}