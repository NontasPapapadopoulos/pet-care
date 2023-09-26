package nondas.pap.petcareapp.domain

import javax.inject.Inject

class ValidateConfirmPassword @Inject constructor() {

    fun execute(password: String, confirmPassword: String): ValidationResult {
        if(password == confirmPassword) {
            return ValidationResult(
                isSuccessful = true,
            )
        }

        return ValidationResult(
            isSuccessful = false,
            errorMessage = "Passwords do not match"

        )
    }
}