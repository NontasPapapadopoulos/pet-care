package nondas.pap.petcareapp.domain.usecase.validator

import javax.inject.Inject

class ConfirmPasswordValidator @Inject constructor() {

    fun validate(password: String, confirmPassword: String): ValidationResult {
        if(password == confirmPassword) {
            return ValidationResult(
                isError = false,
            )
        }

        return ValidationResult(
            isError = true,
            errorMessage = "Passwords do not match"

        )
    }
}