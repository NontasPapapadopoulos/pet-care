package nondas.pap.petcareapp.domain.usecase.validator

import javax.inject.Inject

class PasswordValidator @Inject constructor() {

    private val allowedSpecialCharacters = listOf("~","!","@", "#","$","%", "^","&","*","(",
        ")",",","_","+","-","=","{","}","[","]",":",";","\"","'","<",">",
        ".","?","/","|","\\")

    fun execute(password: String, errorMessage: String = ""): ValidationResult {
        if (hasUppercaseLetter(password) &&
                hasLowercaseLetter(password) &&
                hasDigit(password) &&
                hasSpecialCharacter(password) &&
                hasSixCharacters(password)) {
            return ValidationResult(
                isSuccessful = true,
            )
        }

        return ValidationResult(
            isSuccessful = false,
            errorMessage = errorMessage

        )
    }

    private fun hasSixCharacters(password: String): Boolean {
        return password.length >= 6
    }

    private fun hasUppercaseLetter(password: String): Boolean {
        val regex = Regex("[A-Z]")
        return regex.containsMatchIn(password)
    }

    private fun hasLowercaseLetter(password: String): Boolean {
        val regex = Regex("[a-z]")
        return regex.containsMatchIn(password)
    }

    private fun hasDigit(password: String): Boolean {
        val regex = Regex("\\d")
        return regex.containsMatchIn(password)
    }

    private fun hasSpecialCharacter(password: String): Boolean {
        for (character in password) {
            val passwordContainsSpecialCharacter = allowedSpecialCharacters.contains(character.toString())
            if (passwordContainsSpecialCharacter)
                return true
        }
        return false
    }
}