package nondas.pap.petcareapp.presentation.pet

sealed class PetEvent {
    data class NameEntered(val userInput: String): PetEvent()
    data class AgeEntered(val userInput: String): PetEvent()
    data class TypeEntered(val userInput: String): PetEvent()
    data class BreedEntered(val userInput: String): PetEvent()
    data class IsAboveOneYearOldChanged(val option: Boolean): PetEvent()
    object AddPet: PetEvent()
}
