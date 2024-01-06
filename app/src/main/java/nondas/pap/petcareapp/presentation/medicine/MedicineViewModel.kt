package nondas.pap.petcareapp.presentation.medicine

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
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

    private val selectedMedicine = MutableSharedFlow<Medicine>()
    private val medicines = MutableSharedFlow<List<Medicine>>()

    override val _uiState: StateFlow<MedicineState> = combine(
        medicines,
        selectedMedicine,
    ) { medicines, selectedMedicine ->

        MedicineState(
            medicines = medicines,
            selectedMedicine = selectedMedicine
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = MedicineState(),
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
    )

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