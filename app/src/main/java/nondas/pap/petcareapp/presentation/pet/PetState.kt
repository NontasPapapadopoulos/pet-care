package nondas.pap.petcareapp.presentation.pet

import nondas.pap.petcareapp.domain.ValidationResult

data class PetState(
    val name: String = "",
    val age: String = "",
    val type: String = "",
    val breed: String = "",
    val isAboveOneYearOld: Boolean = false,
    val isAddButtonEnabled: Boolean = false,

    val nameValidation: ValidationResult = ValidationResult(isSuccessful = true),
    val ageValidation: ValidationResult = ValidationResult(isSuccessful = true),
    val typeValidation: ValidationResult = ValidationResult(isSuccessful = true),
    val breedValidation: ValidationResult = ValidationResult(isSuccessful = true)

)
