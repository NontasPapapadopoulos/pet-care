package nondas.pap.petcareapp.presentation.register

import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult

data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val nameValidation: ValidationResult = ValidationResult(isSuccessful = true, errorMessage = ""),
    val emailValidation: ValidationResult = ValidationResult(isSuccessful = true, errorMessage = ""),
    val passwordValidation: ValidationResult = ValidationResult(isSuccessful = true, errorMessage = ""),
    val confirmPasswordValidation: ValidationResult = ValidationResult(isSuccessful = true, errorMessage = ""),
    val isRegisterButtonEnabled: Boolean = false
)
