package nondas.pap.petcareapp.domain.usecase.pet

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import nondas.pap.inventoryapp.domain.FlowUseCase
import nondas.pap.petcareapp.domain.repository.PetRepository
import nondas.pap.petcareapp.domain.executor.IoDispatcher
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import javax.inject.Inject

class GetPets @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val petRepository: PetRepository
): FlowUseCase<List<PetDomainEntity>, GetPets.Params>(dispatcher) {


    override fun invoke(params: Params): Flow<List<PetDomainEntity>> {
        return petRepository.getPets(params.userId)
    }
   data class Params(val userId: String)
}