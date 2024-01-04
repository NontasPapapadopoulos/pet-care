package nondas.pap.petcareapp.presentation.medicine

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import nondas.pap.petcareapp.domain.model.Medicine
import nondas.pap.petcareapp.domain.model.MedicineType
import nondas.pap.petcareapp.domain.model.TimePeriod
import nondas.pap.petcareapp.domain.usecase.validator.DateValidator
import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult
import nondas.pap.petcareapp.presentation.BlocViewModel
import nondas.pap.petcareapp.presentation.ValidatedField
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val dateValidator: DateValidator
): BlocViewModel<MedicineEvent, MedicineState>() {


    private val optionFlow = MutableSharedFlow<String>()
    private val frequencyFlow = MutableSharedFlow<String>()
    private val commentsTextFlow = MutableSharedFlow<String>()
    private val validatedDateFlow = MutableSharedFlow<ValidatedField>()

    override val _uiState: StateFlow<MedicineState>
        get() = TODO("Not yet implemented")


    init {
        on(MedicineEvent.TypeSelected::class) {
            optionFlow.emit(it.option)
        }

        on(MedicineEvent.FrequencySelected::class) {
            frequencyFlow.emit(it.option)
        }

        on(MedicineEvent.CommentsEntered::class) {
            commentsTextFlow.emit(it.userInput)
        }

        on(MedicineEvent.DeleteButtonClicked::class) {

        }

        on(MedicineEvent.UpdateMedicine::class) {

        }

        on(MedicineEvent.AddMedicine::class) {

        }

        on(MedicineEvent.DatePerformedEntered::class) {
            val validationResult = dateValidator.execute(
                date = it.date,
                fieldName = "date",
                comparisonFlag = DateValidator.DATE_COMPARISON_FLAG.NONE
            )
            val validatedField = ValidatedField(value = it.date, validationResult)
            validatedDateFlow.emit(validatedField)
        }


    }

}

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


data class MedicineState(
    val type: String = "",
    val frequency: String = "",
    var date: ValidatedField = ValidatedField(),
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