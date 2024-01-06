package nondas.pap.petcareapp.presentation.medicine.add

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import nondas.pap.petcareapp.domain.model.Medicine
import nondas.pap.petcareapp.domain.model.MedicineType
import nondas.pap.petcareapp.domain.model.TimePeriod
import nondas.pap.petcareapp.domain.usecase.validator.DateValidator
import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult
import nondas.pap.petcareapp.presentation.BlocViewModel
import nondas.pap.petcareapp.presentation.ValidatedField
import javax.inject.Inject

@HiltViewModel
class AddMedicineViewModel @Inject constructor(
    private val dateValidator: DateValidator
): BlocViewModel<AddMedicineEvent, AddMedicineState>() {


    private val optionFlow = MutableSharedFlow<String>()
    private val frequencyFlow = MutableSharedFlow<String>()
    private val commentsTextFlow = MutableSharedFlow<String>()
    private val date = MutableSharedFlow<ValidatedField>()

    override val _uiState: StateFlow<AddMedicineState> = combine(
        optionFlow,
        frequencyFlow,
        commentsTextFlow,
        date
    ) { option, frequency, comments, date ->

        AddMedicineState(
            type = option,
            frequency = frequency,
            comments = comments,
            date = date
        )

    }.stateIn(
        scope = viewModelScope,
        initialValue = AddMedicineState(),
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
    )


    init {
        on(AddMedicineEvent.TypeSelected::class) {
            optionFlow.emit(it.option)
        }

        on(AddMedicineEvent.FrequencySelected::class) {
            frequencyFlow.emit(it.option)
        }

        on(AddMedicineEvent.CommentsEntered::class) {
            commentsTextFlow.emit(it.userInput)
        }

        on(AddMedicineEvent.DatePerformedEntered::class) {
            val validationResult = dateValidator.execute(
                date = it.date,
                fieldName = "date",
                comparisonFlag = DateValidator.DATE_COMPARISON_FLAG.NONE
            )
            val validatedField = ValidatedField(value = it.date, validationResult)
            date.emit(validatedField)
        }

        on(AddMedicineEvent.AddMedicine::class) {

        }

    }

}

sealed class AddMedicineEvent {

    data class TypeSelected(val option: String): AddMedicineEvent()
    data class FrequencySelected(val option: String): AddMedicineEvent()

    data class CommentsEntered(val userInput: String): AddMedicineEvent()
    object AddMedicine: AddMedicineEvent()
    object UpdateMedicine: AddMedicineEvent()

    data class EditButtonClicked(val medicine: Medicine): AddMedicineEvent()
    data class DeleteButtonClicked(val medicine: Medicine): AddMedicineEvent()
    data class DatePerformedEntered(val date: String): AddMedicineEvent()
}


data class AddMedicineState(
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