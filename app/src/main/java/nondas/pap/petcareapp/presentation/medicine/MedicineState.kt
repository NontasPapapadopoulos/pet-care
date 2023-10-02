package nondas.pap.petcareapp.presentation.medicine

import nondas.pap.petcareapp.domain.model.TimePeriod

data class MedicineState(
    val type: String = "",
    val repeat: TimePeriod = TimePeriod.EVERY_YEAR,
    val date: String = "",
    val comments: String = ""
)
