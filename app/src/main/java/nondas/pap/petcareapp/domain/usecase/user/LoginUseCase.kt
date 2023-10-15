package nondas.pap.petcareapp.domain.usecase.user

import nondas.pap.petcareapp.data.repository.AuthenticateRepository
import nondas.pap.petcareapp.domain.model.UserCredentials
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticateRepository: AuthenticateRepository
) {

    suspend fun execute(userCredentials: UserCredentials) {

        authenticateRepository.login(userCredentials)

    }
}