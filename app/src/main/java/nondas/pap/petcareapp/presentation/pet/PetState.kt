package nondas.pap.petcareapp.presentation.pet

import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult

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


    )
