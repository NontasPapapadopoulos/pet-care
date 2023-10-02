package nondas.pap.petcareapp.presentation.pet

import nondas.pap.petcareapp.domain.model.Pet

sealed class PetEvent {
    data class NameEntered(val userInput: String): PetEvent()
    data class DobEntered(val userInput: String): PetEvent()
    data class TypeEntered(val userInput: String): PetEvent()
    data class BreedEntered(val userInput: String): PetEvent()
    data class OptionSelected(val option: Int): PetEvent()
    object AddPet: PetEvent()
    object EditPet: PetEvent()

    data class EditButtonClicked(val pet: Pet): PetEvent()
    data class DeleteButtonClicked(val pet: Pet): PetEvent()
}
