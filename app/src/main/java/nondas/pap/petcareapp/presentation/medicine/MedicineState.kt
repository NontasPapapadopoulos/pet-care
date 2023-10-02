package nondas.pap.petcareapp.presentation.medicine

import nondas.pap.petcareapp.domain.model.Medicine
import nondas.pap.petcareapp.domain.model.TimePeriod

data class MedicineState(
    val type: String = "",
    val types: List<String> = listOf("Vaccine", "Pill", "Other"),
    val frequency: String = "",
    val date: String = "",
    val comments: String = "",
    val petName: String = "",

    val frequencyValues: List<String> = listOf(
        TimePeriod.EVERY_MONTH.name,
        TimePeriod.EVERY_THREE_MONTHS.name,
        TimePeriod.EVERY_SIX_MONTHS.name,
        TimePeriod.EVERY_YEAR.name,
    ),

    val selectedMedicine: Medicine = Medicine()
)
