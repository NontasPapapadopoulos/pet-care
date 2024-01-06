package nondas.pap.petcareapp.domain.usecase.user

import kotlinx.coroutines.CoroutineDispatcher
import nondas.pap.inventoryapp.domain.SuspendUseCase
import nondas.pap.petcareapp.domain.repository.AuthenticateRepository
import nondas.pap.petcareapp.domain.executor.IoDispatcher
import nondas.pap.petcareapp.domain.entity.UserDomainEntity
import javax.inject.Inject

class PerformRegister @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val authenticateRepository: AuthenticateRepository,
): SuspendUseCase<Unit, PerformRegister.Params>(dispatcher) {

    override suspend fun invoke(params: Params) {
        authenticateRepository.register(params.userDomainEntity)
    }

    data class Params(val userDomainEntity: UserDomainEntity)
}