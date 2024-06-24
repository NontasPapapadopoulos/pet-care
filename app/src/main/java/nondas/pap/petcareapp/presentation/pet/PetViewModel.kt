package nondas.pap.petcareapp.presentation.pet

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import nondas.pap.petcareapp.domain.entity.UserDomainEntity
import nondas.pap.petcareapp.domain.usecase.pet.DeletePet
import nondas.pap.petcareapp.domain.usecase.user.GetUser
import nondas.pap.petcareapp.presentation.BlocViewModel
import javax.inject.Inject


@HiltViewModel
class PetViewModel @Inject constructor(
    private val deletePet: DeletePet,
    private val getUser: GetUser,
): BlocViewModel<PetEvent, PetState>() {

    private val pets = MutableSharedFlow<List<PetDomainEntity>>()
    private val selectedPetDomainEntity = MutableSharedFlow<PetDomainEntity>()

    private val user = getUser.execute(Unit)
        .map { it.getOrThrow() }
        .catch { addError(it) }

    override val _uiState: StateFlow<PetState> = combine(
        pets.onStart { emit(listOf()) },
        selectedPetDomainEntity,
        user
    ) { pets, selectedPet, user ->

        PetState.Content(
            pets = pets,
            selectedPetDomainEntity = selectedPet,
            user = user
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = PetState.Idle
    )

    init {

        on(PetEvent.DeleteButtonClicked::class) {
            deletePet.execute(DeletePet.Params(it.petDomainEntity))
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


sealed interface PetState {
    object Idle: PetState
    data class Content(
        val pets: List<PetDomainEntity>,
        val selectedPetDomainEntity: PetDomainEntity?,
        val user: UserDomainEntity
    ): PetState
}

