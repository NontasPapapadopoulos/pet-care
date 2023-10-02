package nondas.pap.petcareapp.presentation.medicine

import nondas.pap.petcareapp.domain.model.Medicine
import nondas.pap.petcareapp.domain.model.Pet
import nondas.pap.petcareapp.presentation.pet.PetEvent

sealed class MedicineEvent {

    data class TypeSelected(val option: Int): MedicineEvent()
    data class FrequencySelected(val option: Int): MedicineEvent()

    data class CommentsEntered(val userInput: String): MedicineEvent()
    object AddMedicine: MedicineEvent()
    object EditMedicine: MedicineEvent()

    data class EditButtonClicked(val medicine: Medicine): MedicineEvent()
    data class DeleteButtonClicked(val medicine: Medicine): MedicineEvent()
}
