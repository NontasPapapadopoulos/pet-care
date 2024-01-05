package nondas.pap.petcareapp.presentation.pet.add

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import nondas.pap.petcareapp.domain.model.Pet
import nondas.pap.petcareapp.domain.usecase.validator.DateValidator
import nondas.pap.petcareapp.domain.usecase.validator.NameValidator
import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult
import nondas.pap.petcareapp.presentation.BlocViewModel
import nondas.pap.petcareapp.presentation.ValidatedField
import nondas.pap.petcareapp.presentation.register.RegisterState
import javax.inject.Inject


@HiltViewModel
class AddPetViewModel @Inject constructor(
    private val nameValidator: NameValidator,
    private val dateValidator: DateValidator
): BlocViewModel<AddPetEvent, AddPetState>() {

    private val nameTextFlow = MutableSharedFlow<ValidatedField>()
    private val petOptionFlow = MutableSharedFlow<String>()
    private val validatedDateFlow = MutableSharedFlow<ValidatedField>()
    private val breedTextFlow = MutableSharedFlow<ValidatedField>()
    private val petTypeFlow = MutableSharedFlow<String>()

    override val _uiState: StateFlow<AddPetState> = combine(
        nameTextFlow,
        petOptionFlow,
        validatedDateFlow,
        breedTextFlow
    ) { name, option, date, breed ->

        AddPetState(
            name = name,
            dob = date,
            breed = breed,
            type = option
        )

    }.stateIn(
        scope = viewModelScope,
        initialValue = AddPetState(),
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
    )


    init {

        on(AddPetEvent.NameEntered::class) {
            val validationResult = nameValidator.execute(it.userInput)
            val validatedField = ValidatedField(value = it.userInput, validationResult)
            nameTextFlow.emit(validatedField)
        }

        on(AddPetEvent.OptionSelected::class) {
            petOptionFlow.emit(it.option)
        }

        on(AddPetEvent.DobEntered::class) {
            val validationResult = dateValidator.execute(
                date = it.userInput,
                fieldName = "date of birth",
                comparisonFlag = DateValidator.DATE_COMPARISON_FLAG.BEFORE_PRESENT_DATE
            )

            val validatedField = ValidatedField(value = it.userInput, validationResult)
            validatedDateFlow.emit(validatedField)
        }

        on(AddPetEvent.BreedEntered::class) {
            val validationResult = nameValidator.execute(it.userInput)
            val validatedField = ValidatedField(value = it.userInput, validationResult)
            breedTextFlow.emit(validatedField)
        }



        on(AddPetEvent.AddPet::class) {
            addPet()
        }



    }


    private fun editPet() {
        TODO("Not yet implemented")
    }

    private fun isAboveOneYearOld(selectedOption: Int): Boolean {
        val option = uiState.value.options[selectedOption]
        if (option.lowercase() == "yes" )
            return true
        return false
    }


    private fun addPet() {
        TODO("Not yet implemented")
    }

}

sealed class AddPetEvent {
    data class NameEntered(val userInput: String): AddPetEvent()
    data class DobEntered(val userInput: String): AddPetEvent()
    data class TypeSelected(val userInput: String): AddPetEvent()
    data class BreedEntered(val userInput: String): AddPetEvent()
    data class OptionSelected(val option: String): AddPetEvent()
    object AddPet: AddPetEvent()

}

data class AddPetState(
    val name: ValidatedField = ValidatedField(),
    val dob:  ValidatedField = ValidatedField(),
    val type:  String = "",
    val breed:  ValidatedField = ValidatedField(),
    val isAboveOneYearOld: Boolean = false,
    val isAddButtonEnabled: Boolean = false,
    val options: List<String> = listOf("Yes", "No"),
    val petTypes: List<String> = listOf("Dog", "Cat"),

    val selectedOption: Int = 0,

    val selectedPet: Pet = Pet()
)