package nondas.pap.petcareapp.presentation.pet.edit

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import nondas.pap.petcareapp.domain.model.Pet
import nondas.pap.petcareapp.domain.usecase.validator.DateValidator
import nondas.pap.petcareapp.domain.usecase.validator.NameValidator
import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult
import nondas.pap.petcareapp.presentation.BlocViewModel
import nondas.pap.petcareapp.presentation.ValidatedField
import javax.inject.Inject


@HiltViewModel
class EditPetViewModel @Inject constructor(
    private val nameValidator: NameValidator,
    private val dateValidator: DateValidator
): BlocViewModel<EditPetEvent, EditPetState>() {

    private val nameTextFlow = MutableSharedFlow<ValidatedField>()
    private val petOptionFlow = MutableSharedFlow<String>()
    private val validatedDateFlow = MutableSharedFlow<ValidatedField>()
    private val breedTextFlow = MutableSharedFlow<ValidatedField>()
    private val petTypeFlow = MutableSharedFlow<String>()

    override val _uiState: StateFlow<EditPetState>
        get() = TODO("Not yet implemented")


    init {

        on(EditPetEvent.NameEntered::class) {
            val validationResult = nameValidator.execute(it.userInput)
            val validatedField = ValidatedField(value = it.userInput, validationResult)
            nameTextFlow.emit(validatedField)
        }

        on(EditPetEvent.OptionSelected::class) {
            petOptionFlow.emit(it.option)
        }

        on(EditPetEvent.DobEntered::class) {
            val validationResult = dateValidator.execute(
                date = it.userInput,
                fieldName = "date of birth",
                comparisonFlag = DateValidator.DATE_COMPARISON_FLAG.BEFORE_PRESENT_DATE
            )

            val validatedField = ValidatedField(value = it.userInput, validationResult)
            validatedDateFlow.emit(validatedField)
        }

        on(EditPetEvent.BreedEntered::class) {
            val validationResult = nameValidator.execute(it.userInput)
            val validatedField = ValidatedField(value = it.userInput, validationResult)
            breedTextFlow.emit(validatedField)
        }

        on(EditPetEvent.TypeSelected::class) {
            petOptionFlow.emit(it.userInput)
        }


        on(EditPetEvent.EditButtonClicked::class) {
            it.pet
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

sealed class EditPetEvent {
    data class NameEntered(val userInput: String): EditPetEvent()
    data class DobEntered(val userInput: String): EditPetEvent()
    data class TypeSelected(val userInput: String): EditPetEvent()
    data class BreedEntered(val userInput: String): EditPetEvent()
    data class OptionSelected(val option: String): EditPetEvent()
    data class EditButtonClicked(val pet: Pet): EditPetEvent()
}

data class EditPetState(
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