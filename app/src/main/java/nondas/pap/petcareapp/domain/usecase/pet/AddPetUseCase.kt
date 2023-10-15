package nondas.pap.petcareapp.domain.usecase.pet

import nondas.pap.petcareapp.data.repository.PetRepository
import nondas.pap.petcareapp.domain.model.Pet
import javax.inject.Inject

class AddPetUseCase @Inject constructor(
    private val petRepository: PetRepository
) {

    suspend fun execute(pet: Pet) {

        petRepository.addPet(pet)

    }
}