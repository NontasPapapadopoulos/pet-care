package nondas.pap.petcareapp.presentation.medicine.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import nondas.pap.petcareapp.domain.entity.MedicineDomainEntity
import nondas.pap.petcareapp.domain.entity.MedicineType
import nondas.pap.petcareapp.domain.entity.TimePeriod
import nondas.pap.petcareapp.domain.usecase.validator.DateValidator
import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult
import nondas.pap.petcareapp.presentation.BlocViewModel
import nondas.pap.petcareapp.presentation.ValidatedField
import nondas.pap.petcareapp.presentation.navigation.NavArg
import javax.inject.Inject

@HiltViewModel
class EditMedicineViewModel @Inject constructor(
    private val editMedicineEvent: EditMedicineEvent,
    private val dateValidator: DateValidator,
    private val savedStateHandle: SavedStateHandle
): BlocViewModel<EditMedicineEvent, EditMedicineState>() {

    private val medicineId get() = savedStateHandle.get<String>(NavArg.medicineId.param)


    private val optionFlow = MutableSharedFlow<String>()
    private val frequencyFlow = MutableSharedFlow<String>()
    private val commentsTextFlow = MutableSharedFlow<String>()
    private val date = MutableSharedFlow<ValidatedField>()

    override val _uiState: StateFlow<EditMedicineState> = combine(
        optionFlow,
        frequencyFlow,
        commentsTextFlow,
        date
    ) { option, frequency, comments, date ->

        EditMedicineState(
            type = option,
            frequency = frequency,
            comments = comments,
            date = date
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = EditMedicineState(),
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
    )

    init {
        on(EditMedicineEvent.TypeSelected::class) {
            optionFlow.emit(it.option)
        }

        on(EditMedicineEvent.FrequencySelected::class) {
            frequencyFlow.emit(it.option)
        }

        on(EditMedicineEvent.CommentsEntered::class) {
            commentsTextFlow.emit(it.userInput)
        }

        on(EditMedicineEvent.UpdateMedicine::class) {

        }

        on(EditMedicineEvent.DatePerformedEntered::class) {
            val validationResult = dateValidator.execute(
                date = it.date,
                fieldName = "date",
                comparisonFlag = DateValidator.DATE_COMPARISON_FLAG.NONE
            )
            val validatedField = ValidatedField(value = it.date, validationResult)
            date.emit(validatedField)
        }

    }

}

sealed class EditMedicineEvent {

    data class TypeSelected(val option: String): EditMedicineEvent()
    data class FrequencySelected(val option: String): EditMedicineEvent()

    data class CommentsEntered(val userInput: String): EditMedicineEvent()
    object AddMedicine: EditMedicineEvent()
    object UpdateMedicine: EditMedicineEvent()

    data class DatePerformedEntered(val date: String): EditMedicineEvent()
}


data class EditMedicineState(
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

    val selectedMedicineDomainEntity: MedicineDomainEntity = MedicineDomainEntity(),

    val dateValidation: ValidationResult = ValidationResult(true, "")
)