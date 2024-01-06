package nondas.pap.petcareapp.presentation.pet

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import nondas.pap.petcareapp.domain.usecase.pet.DeletePet
import nondas.pap.petcareapp.presentation.BlocViewModel
import javax.inject.Inject


@HiltViewModel
class PetViewModel @Inject constructor(
    private val deletePet: DeletePet
): BlocViewModel<PetEvent, PetState>() {

    private val pets = MutableSharedFlow<List<PetDomainEntity>>()
    private val selectedPetDomainEntity = MutableSharedFlow<PetDomainEntity>()

    override val _uiState: StateFlow<PetState> = combine(
        pets.onStart { emit(listOf()) },
        selectedPetDomainEntity
    ) { pets, selectedPet ->

        PetState(
            petDomainEntities = pets,
            selectedPetDomainEntity = selectedPet
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = PetState(),
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
    )

    init {

        on(PetEvent.DeleteButtonClicked::class) {
            it.petDomainEntity
        }

        on(PetEvent.EditButtonClicked::class) {
            it.petDomainEntity
        }

    }

}

sealed class PetEvent {
    object AddPet: PetEvent()
    data class EditButtonClicked(val petDomainEntity: PetDomainEntity): PetEvent()
    data class DeleteButtonClicked(val petDomainEntity: PetDomainEntity): PetEvent()
}

data class PetState(
    val petDomainEntities: List<PetDomainEntity> = listOf(),
    val selectedPetDomainEntity: PetDomainEntity = PetDomainEntity()
)