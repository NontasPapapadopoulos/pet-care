package nondas.pap.petcareapp.presentation.pet.add

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import nondas.pap.petcareapp.domain.usecase.pet.AddPet
import nondas.pap.petcareapp.domain.usecase.validator.DateValidator
import nondas.pap.petcareapp.domain.usecase.validator.NameValidator
import nondas.pap.petcareapp.presentation.BlocViewModel
import nondas.pap.petcareapp.presentation.ValidatedField
import javax.inject.Inject


@HiltViewModel
class AddPetViewModel @Inject constructor(
    private val addPet: AddPet,
    private val nameValidator: NameValidator,
    private val dateValidator: DateValidator
): BlocViewModel<AddPetEvent, AddPetsState>() {

    private val nameTextFlow = MutableSharedFlow<ValidatedField>()
    private val petKindFlow = MutableSharedFlow<String>()
    private val validatedDateFlow = MutableSharedFlow<ValidatedField>()
    private val isAddButtonEnabledFlow = MutableSharedFlow<Boolean>()

    override val _uiState: StateFlow<AddPetsState> = combine(
        nameTextFlow.onStart { emit(ValidatedField()) },
        petKindFlow.onStart { emit("") },
        validatedDateFlow.onStart { emit(ValidatedField()) },
        isAddButtonEnabledFlow.onStart { emit(false) }
    ) { name, option, date, isAddButtonEnabled ->

        AddPetsState.Content(
            name = name,
            dob = date,
            kind = option,
            isAddButtonEnabled = isAddButtonEnabled
        )

    }.stateIn(
        scope = viewModelScope,
        initialValue = AddPetsState.Idle,
        started = SharingStarted.WhileSubscribed()
    )


    init {
        on(AddPetEvent.NameEntered::class) {
            val validationResult = nameValidator.validate(it.userInput)
            val validatedField = ValidatedField(value = it.userInput, validationResult)
            nameTextFlow.emit(validatedField)

            validateForm()
        }


        on(AddPetEvent.DobEntered::class) {
            val validationResult = dateValidator.execute(
                date = it.userInput,
                fieldName = "date of birth",
                comparisonFlag = DateValidator.DATE_COMPARISON_FLAG.BEFORE_PRESENT_DATE
            )

            val validatedField = ValidatedField(value = it.userInput, validationResult)
            validatedDateFlow.emit(validatedField)

            validateForm()
        }

        on(AddPetEvent.KindSelected::class) {
            petKindFlow.emit(it.userInput)
            validateForm()
        }

        on(AddPetEvent.AddPet::class) {
            onState<AddPetsState.Content> { state ->
                addPet.execute(
                    params = AddPet.Params(
                        name = state.name.value,
                        kind = state.kind,
                        dob = state.dob.value
                    )
                )
            }
        }


    }


    private suspend fun validateForm() {
        onState<AddPetsState.Content> { state ->
            val isFormValid = !state.dob.validation.isError &&
                    !state.name.validation.isError &&
                    state.kind.isNotEmpty()
            isAddButtonEnabledFlow.emit(isFormValid)
        }
    }
}

sealed class AddPetEvent {
    data class NameEntered(val userInput: String): AddPetEvent()
    data class DobEntered(val userInput: String): AddPetEvent()
    data class KindSelected(val userInput: String): AddPetEvent()
    object AddPet: AddPetEvent()

}

sealed interface AddPetsState {
    object Idle: AddPetsState
    data class Content(
        val name: ValidatedField = ValidatedField(),
        val dob:  ValidatedField = ValidatedField(),
        val kind:  String = "",
        val isAddButtonEnabled: Boolean,
        val petTypes: List<String> = listOf("Dog", "Cat"),
    ): AddPetsState
}
