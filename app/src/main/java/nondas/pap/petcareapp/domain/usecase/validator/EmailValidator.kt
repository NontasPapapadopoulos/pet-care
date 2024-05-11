package nondas.pap.petcareapp.domain.usecase.validator

import android.util.Patterns
import javax.inject.Inject

class EmailValidator @Inject constructor() {

    fun validate(
        email: String,
        isMandatory: Boolean = true
    ): ValidationResult {


        if(isMandatory && email.isBlank()) {
            return ValidationResult(
                isError = true,
                errorMessage = "The email can't be blank"
            )
        }
       if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                isError = true,
                errorMessage = "That's not a valid email"
            )
        }
        return ValidationResult(
            isError = false
        )
    }
}