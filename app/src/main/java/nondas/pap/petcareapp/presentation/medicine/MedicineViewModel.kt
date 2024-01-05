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
import nondas.pap.petcareapp.domain.model.Pet
import nondas.pap.petcareapp.domain.model.TimePeriod
import nondas.pap.petcareapp.domain.usecase.validator.DateValidator
import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult
import nondas.pap.petcareapp.presentation.BlocViewModel
import nondas.pap.petcareapp.presentation.ValidatedField
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
): BlocViewModel<MedicineEvent, MedicineState>() {



    override val _uiState: StateFlow<MedicineState>
        get() = TODO("Not yet implemented")


    init {
        on(MedicineEvent.DeleteButtonClicked::class) {

        }

        on(MedicineEvent.EditButtonClicked::class) {

        }

    }
}

sealed class MedicineEvent {
    object AddMedicine: MedicineEvent()
    data class EditButtonClicked(val medicine: Medicine): MedicineEvent()
    data class DeleteButtonClicked(val medicine: Medicine): MedicineEvent()
}


data class MedicineState(
    val selectedMedicine: Medicine = Medicine(),
    val medicines: List<Medicine> = listOf()
)