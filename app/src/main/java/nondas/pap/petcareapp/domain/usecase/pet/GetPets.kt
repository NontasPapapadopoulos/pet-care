package nondas.pap.petcareapp.domain.usecase.pet

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import nondas.pap.inventoryapp.domain.FlowUseCase
import nondas.pap.petcareapp.domain.repository.PetRepository
import nondas.pap.petcareapp.domain.executor.IoDispatcher
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import nondas.pap.petcareapp.domain.repository.UserRepository
import javax.inject.Inject

class GetPets @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val petRepository: PetRepository,
    private val userRepository: UserRepository,
): FlowUseCase<List<PetDomainEntity>, Unit>(dispatcher) {
    override fun invoke(params: Unit): Flow<List<PetDomainEntity>> {
        return flow {
            val user = userRepository.getCurrentUser()
            petRepository.getPets(user.userId)
        }
    }
}


//userRepository.getCurrentUserFlow().flatMapMerge {
//    println("user: $it")
//
//    println(petRepository.getPets(it.userId))
//    petRepository.getPets(it.userId)
//}