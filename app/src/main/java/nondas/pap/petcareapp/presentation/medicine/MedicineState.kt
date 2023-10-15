package nondas.pap.petcareapp.presentation.medicine

import nondas.pap.petcareapp.domain.model.Medicine
import nondas.pap.petcareapp.domain.model.MedicineType
import nondas.pap.petcareapp.domain.model.TimePeriod
import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult

data class MedicineState(
    val type: String = "",
    val frequency: String = "",
    var date: String = "",
    val comments: String = "",
    val petName: String = "",

    val types: List<String> = listOf(
        MedicineType.VACCINE.type,
        MedicineType.PILL.type,
        MedicineType.EXAMINATION.type,
        MedicineType.OTHER.type
    ),

    val frequencyValues: List<String> = listOf(
        TimePeriod.EVERY_MONTH.value,
        TimePeriod.EVERY_THREE_MONTHS.value,
        TimePeriod.EVERY_SIX_MONTHS.value,
        TimePeriod.EVERY_YEAR.value,
    ),

    val selectedMedicine: Medicine = Medicine(),

    val dateValidation: ValidationResult = ValidationResult(true, "")
)
