package nondas.pap.petcareapp.domain.usecase.user

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import nondas.pap.inventoryapp.domain.FlowUseCase
import nondas.pap.petcareapp.domain.executor.IoDispatcher
import nondas.pap.petcareapp.domain.entity.UserDomainEntity
import nondas.pap.petcareapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUser @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository
): FlowUseCase<UserDomainEntity, Unit>(dispatcher) {

    override fun invoke(params: Unit): Flow<UserDomainEntity> {
        return userRepository.getCurrentUserFlow()
    }

}