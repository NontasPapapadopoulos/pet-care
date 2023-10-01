package nondas.pap.petcareapp.presentation.pet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nondas.pap.petcareapp.domain.usecase.validator.NameValidator
import javax.inject.Inject


@HiltViewModel
class PetViewModel @Inject constructor(
    private val nameValidator: NameValidator
): ViewModel() {


    var state by mutableStateOf(PetState())


    fun onEvent(event: PetEvent) {
        when(event) {
            is PetEvent.NameEntered -> {
                val validationResult = nameValidator.execute(event.userInput)

                state = state.copy(
                    name = event.userInput,
                    nameValidation = validationResult
                )
            }

            is PetEvent.OptionSelected -> {
                state = state.copy(
                    isAboveOneYearOld = isAboveOneYearOld(event.option)
                )
            }


            is PetEvent.DobEntered -> {
                state = state.copy(dob = event.userInput)
            }

            is PetEvent.BreedEntered -> {
                val validationResult = nameValidator.execute(event.userInput)
                state = state.copy(
                    breed = event.userInput,
                    breedValidation = validationResult
                )
            }



            is PetEvent.TypeEntered -> {
                state = state.copy(type = event.userInput)
            }

            is PetEvent.AddPet -> {
                addPet()
            }

            is PetEvent.EditPet -> {
                editPet()
            }

        }
    }

    private fun editPet() {
        TODO("Not yet implemented")
    }

    private fun isAboveOneYearOld(selectedOption: Int): Boolean {
        val option = state.options[selectedOption]
        if (option.lowercase() == "yes" )
            return true
        return false
    }


    private fun addPet() {
        TODO("Not yet implemented")
    }

}