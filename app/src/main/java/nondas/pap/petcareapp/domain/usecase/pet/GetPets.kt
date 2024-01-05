package nondas.pap.petcareapp.domain.usecase.pet

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import nondas.pap.inventoryapp.domain.FlowUseCase
import nondas.pap.petcareapp.domain.repository.PetRepository
import nondas.pap.petcareapp.domain.executor.IoDispatcher
import nondas.pap.petcareapp.domain.model.Pet
import javax.inject.Inject

class GetPets @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val petRepository: PetRepository
): FlowUseCase<List<Pet>, Unit>(dispatcher) {


    override fun invoke(params: Unit): Flow<List<Pet>> {
        return petRepository.getPets(11)
            .catch { emit(listOf()) }
    }

}