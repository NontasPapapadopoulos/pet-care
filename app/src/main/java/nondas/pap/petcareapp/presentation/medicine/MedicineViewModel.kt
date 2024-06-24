package nondas.pap.petcareapp.presentation.medicine

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import nondas.pap.petcareapp.domain.entity.MedicineDomainEntity
import nondas.pap.petcareapp.presentation.BlocViewModel
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
): BlocViewModel<MedicineEvent, MedicineState>() {

    private val selectedMedicineDomainEntity = MutableSharedFlow<MedicineDomainEntity?>()
    private val medicines = MutableSharedFlow<List<MedicineDomainEntity>>()

    override val _uiState: StateFlow<MedicineState> = combine(
        medicines.onStart { emit(listOf()) },
        selectedMedicineDomainEntity.onStart { emit(null) },
    ) { medicines, selectedMedicine ->

        MedicineState.Content(
            medicines = medicines,
            selectedMedicine = selectedMedicine
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = MedicineState.Idle
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
    data class EditButtonClicked(val medicineDomainEntity: MedicineDomainEntity): MedicineEvent()
    data class DeleteButtonClicked(val medicineDomainEntity: MedicineDomainEntity): MedicineEvent()
}


sealed interface MedicineState {

    object Idle: MedicineState

    data class Content(
        val selectedMedicine: MedicineDomainEntity?,
        val medicines: List<MedicineDomainEntity>
    ): MedicineState

}
