package nondas.pap.petcareapp.presentation.medicine

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nondas.pap.petcareapp.domain.usecase.validator.DateValidator
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val dateValidator: DateValidator
): ViewModel() {


    var state by mutableStateOf(MedicineState())



    fun onEvent(event: MedicineEvent) {
        when(event) {

            is MedicineEvent.TypeSelected -> {
                state = state.copy(type = event.option)
            }

            is MedicineEvent.FrequencySelected -> {
                state = state.copy(frequency = event.option)
            }

            is MedicineEvent.CommentsEntered -> {
                state = state.copy(comments = event.userInput)
            }

            is MedicineEvent.DeleteButtonClicked -> {


            }

            is MedicineEvent.EditButtonClicked -> {

            }

            is MedicineEvent.UpdateMedicine -> {

            }


            is MedicineEvent.AddMedicine -> {

            }

            is MedicineEvent.DatePerformedEntered -> {
                val validationResult = dateValidator.execute(
                    date = event.date,
                    fieldName = "date",
                    comparisonFlag = DateValidator.DATE_COMPARISON_FLAG.NONE
                )

                state = state.copy(
                    date = event.date,
                    dateValidation = validationResult
                 )
            }
        }
    }

}