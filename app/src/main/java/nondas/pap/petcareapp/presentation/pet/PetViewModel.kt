package nondas.pap.petcareapp.presentation.pet

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import nondas.pap.petcareapp.domain.model.Pet
import nondas.pap.petcareapp.domain.usecase.pet.DeletePet
import nondas.pap.petcareapp.domain.usecase.validator.DateValidator
import nondas.pap.petcareapp.domain.usecase.validator.NameValidator
import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult
import nondas.pap.petcareapp.presentation.BlocViewModel
import nondas.pap.petcareapp.presentation.ValidatedField
import nondas.pap.petcareapp.presentation.register.RegisterState
import javax.inject.Inject


@HiltViewModel
class PetViewModel @Inject constructor(
    private val deletePet: DeletePet
): BlocViewModel<PetEvent, PetState>() {

    private val pets = MutableSharedFlow<List<Pet>>()
    private val selectedPet = MutableSharedFlow<Pet>()

    override val _uiState: StateFlow<PetState> = combine(
        pets.onStart { emit(listOf()) },
        selectedPet
    ) { pets, selectedPet ->

        PetState(
            pets = pets,
            selectedPet = selectedPet
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = PetState(),
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
    )

    init {

        on(PetEvent.DeleteButtonClicked::class) {
            it.pet
        }

        on(PetEvent.EditButtonClicked::class) {
            it.pet
        }

    }

}

sealed class PetEvent {
    object AddPet: PetEvent()
    data class EditButtonClicked(val pet: Pet): PetEvent()
    data class DeleteButtonClicked(val pet: Pet): PetEvent()
}

data class PetState(
    val pets: List<Pet> = listOf(),
    val selectedPet: Pet = Pet()
)