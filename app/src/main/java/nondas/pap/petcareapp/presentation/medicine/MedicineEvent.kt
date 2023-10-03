package nondas.pap.petcareapp.presentation.medicine

import nondas.pap.petcareapp.domain.model.Medicine

sealed class MedicineEvent {

    data class TypeSelected(val option: String): MedicineEvent()
    data class FrequencySelected(val option: String): MedicineEvent()

    data class CommentsEntered(val userInput: String): MedicineEvent()
    object AddMedicine: MedicineEvent()
    object UpdateMedicine: MedicineEvent()

    data class EditButtonClicked(val medicine: Medicine): MedicineEvent()
    data class DeleteButtonClicked(val medicine: Medicine): MedicineEvent()
    data class DatePerformedEntered(val date: String): MedicineEvent()
}
