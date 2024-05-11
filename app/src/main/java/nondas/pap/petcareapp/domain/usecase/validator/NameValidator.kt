package nondas.pap.petcareapp.domain.usecase.validator

import javax.inject.Inject

class NameValidator @Inject constructor() {

    fun validate(firstName: String): ValidationResult {

        if(firstName.trim().isNotBlank() &&
            containsOnlyLatinCharacters(firstName)) {
            return ValidationResult(
                isError = false
            )
        }

        return ValidationResult(
            isError = true,
            errorMessage = "Name should contain only latin characters"

        )
    }

    private fun containsOnlyLatinCharacters(fullName: String): Boolean {
        val regex = Regex("[a-zA-Z ]+")
        return fullName.matches(regex)
    }
}