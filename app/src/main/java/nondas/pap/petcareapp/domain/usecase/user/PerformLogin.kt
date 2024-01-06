package nondas.pap.petcareapp.domain.usecase.user

import kotlinx.coroutines.CoroutineDispatcher
import nondas.pap.inventoryapp.domain.SuspendUseCase
import nondas.pap.petcareapp.domain.entity.UserCredentials
import nondas.pap.petcareapp.domain.repository.AuthenticateRepository
import nondas.pap.petcareapp.domain.executor.IoDispatcher
import nondas.pap.petcareapp.domain.entity.UserDomainEntity
import javax.inject.Inject

open class PerformLogin @Inject constructor(
    private val authenticateRepository: AuthenticateRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): SuspendUseCase<Unit, PerformLogin.Params>(dispatcher) {


    override suspend fun invoke(params: Params) {
        val userCredentials = UserCredentials(email = params.email, password = params.password)
        authenticateRepository.login(userCredentials)
    }


    data class Params(val email: String, val password: String)
}