package nondas.pap.petcareapp.domain.usecase.pet

import kotlinx.coroutines.CoroutineDispatcher
import nondas.pap.inventoryapp.domain.SuspendUseCase
import nondas.pap.petcareapp.domain.repository.PetRepository
import nondas.pap.petcareapp.domain.executor.IoDispatcher
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import javax.inject.Inject


class EditPet @Inject constructor(
    private val petRepository: PetRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): SuspendUseCase<Unit, EditPet.Params>(dispatcher) {

    override suspend fun invoke(params: Params) {
        petRepository.editPet(params.petDomainEntity)
    }

    data class Params(val petDomainEntity: PetDomainEntity)
}