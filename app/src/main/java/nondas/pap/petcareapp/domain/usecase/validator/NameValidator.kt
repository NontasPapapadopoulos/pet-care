package nondas.pap.petcareapp.domain.usecase.validator

import javax.inject.Inject

class NameValidator @Inject constructor() {

    fun execute(firstName: String): ValidationResult {

        if(firstName.trim().isNotBlank() &&
            containsOnlyLatinCharacters(firstName)) {
            return ValidationResult(
                isSuccessful = true
            )
        }

        return ValidationResult(
            isSuccessful = false,
            errorMessage = "Name should contain only latin characters"

        )
    }

    private fun containsOnlyLatinCharacters(fullName: String): Boolean {
        val regex = Regex("[a-zA-Z ]+")
        return fullName.matches(regex)
    }
}