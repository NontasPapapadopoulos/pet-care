package nondas.pap.petcareapp.presentation.pet

sealed class PetEvent {
    data class NameEntered(val userInput: String): PetEvent()
    data class DobEntered(val userInput: String): PetEvent()
    data class TypeEntered(val userInput: String): PetEvent()
    data class BreedEntered(val userInput: String): PetEvent()
    data class OptionSelected(val option: Int): PetEvent()
    object AddPet: PetEvent()
}
