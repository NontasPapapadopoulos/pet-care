package nondas.pap.petcareapp.presentation.pet

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
class PetViewModel @Inject constructor(
    private val nameValidator: NameValidator,
    private val dateValidator: DateValidator
): BlocViewModel<PetEvent, PetState>() {

    private val nameTextFlow = MutableSharedFlow<ValidatedField>()
    private val petOptionFlow = MutableSharedFlow<String>()
    private val validatedDateFlow = MutableSharedFlow<ValidatedField>()
    private val breedTextFlow = MutableSharedFlow<ValidatedField>()
    private val petTypeFlow = MutableSharedFlow<String>()

    override val _uiState: StateFlow<PetState>
        get() = TODO("Not yet implemented")


    init {

        on(PetEvent.NameEntered::class) {
            val validationResult = nameValidator.execute(it.userInput)
            val validatedField = ValidatedField(value = it.userInput, validationResult)
            nameTextFlow.emit(validatedField)
        }

        on(PetEvent.OptionSelected::class) {
            petOptionFlow.emit(it.option)
        }

        on(PetEvent.DobEntered::class) {
            val validationResult = dateValidator.execute(
                date = it.userInput,
                fieldName = "date of birth",
                comparisonFlag = DateValidator.DATE_COMPARISON_FLAG.BEFORE_PRESENT_DATE
            )

            val validatedField = ValidatedField(value = it.userInput, validationResult)
            validatedDateFlow.emit(validatedField)
        }

        on(PetEvent.BreedEntered::class) {
            val validationResult = nameValidator.execute(it.userInput)
            val validatedField = ValidatedField(value = it.userInput, validationResult)
            breedTextFlow.emit(validatedField)
        }

        on(PetEvent.TypeSelected::class) {
            petOptionFlow.emit(it.userInput)
        }

        on(PetEvent.AddPet::class) {
            addPet()
        }

        on(PetEvent.EditPet::class) {
            editPet()
        }

        on(PetEvent.DeleteButtonClicked::class) {
            it.pet
        }

        on(PetEvent.EditButtonClicked::class) {
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

sealed class PetEvent {
    data class NameEntered(val userInput: String): PetEvent()
    data class DobEntered(val userInput: String): PetEvent()
    data class TypeSelected(val userInput: String): PetEvent()
    data class BreedEntered(val userInput: String): PetEvent()
    data class OptionSelected(val option: String): PetEvent()
    object AddPet: PetEvent()
    object EditPet: PetEvent()

    data class EditButtonClicked(val pet: Pet): PetEvent()
    data class DeleteButtonClicked(val pet: Pet): PetEvent()
}

data class PetState(
    val name: String = "",
    val dob: String = "",
    val type: String = "",
    val breed: String = "",
    val isAboveOneYearOld: Boolean = false,
    val isAddButtonEnabled: Boolean = false,
    val options: List<String> = listOf("Yes", "No"),
    val petTypes: List<String> = listOf("Dog", "Cat"),

    val selectedOption: Int = 0,

    val nameValidation: ValidationResult = ValidationResult(isSuccessful = true),
    val dobValidation: ValidationResult = ValidationResult(isSuccessful = true),
    val typeValidation: ValidationResult = ValidationResult(isSuccessful = true),
    val breedValidation: ValidationResult = ValidationResult(isSuccessful = true),


    val selectedPet: Pet = Pet()
)