package nondas.pap.petcareapp.presentation.pet

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import nondas.pap.petcareapp.domain.model.Pet
import nondas.pap.petcareapp.domain.usecase.pet.DeletePet
import nondas.pap.petcareapp.domain.usecase.validator.DateValidator
import nondas.pap.petcareapp.domain.usecase.validator.NameValidator
import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult
import nondas.pap.petcareapp.presentation.BlocViewModel
import nondas.pap.petcareapp.presentation.ValidatedField
import javax.inject.Inject


@HiltViewModel
class PetViewModel @Inject constructor(
    private val deletePet: DeletePet
): BlocViewModel<PetEvent, PetState>() {


    override val _uiState: StateFlow<PetState>
        get() = TODO("Not yet implemented")


    init {

        on(PetEvent.DeleteButtonClicked::class) {
            it.pet
        }

        on(PetEvent.EditButtonClicked::class) {
            it.pet
        }

    }


    private fun addPet() {
        TODO("Not yet implemented")
    }

}

sealed class PetEvent {
    object AddPet: PetEvent()
    data class EditButtonClicked(val pet: Pet): PetEvent()
    data class DeleteButtonClicked(val pet: Pet): PetEvent()
}

data class PetState(
    val pets: List<Pet> = listOf(),
    val selectedPet: Pet
)