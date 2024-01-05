package nondas.pap.petcareapp.domain.usecase.pet

import kotlinx.coroutines.CoroutineDispatcher
import nondas.pap.inventoryapp.domain.SuspendUseCase
import nondas.pap.petcareapp.domain.repository.PetRepository
import nondas.pap.petcareapp.domain.executor.IoDispatcher
import nondas.pap.petcareapp.domain.model.Pet
import javax.inject.Inject


class EditPet @Inject constructor(
    private val petRepository: PetRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): SuspendUseCase<Unit, EditPet.Params>(dispatcher) {

    override suspend fun invoke(params: Params) {
        petRepository.editPet(params.pet)
    }

    data class Params(val pet: Pet)
}