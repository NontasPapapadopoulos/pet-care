package nondas.pap.petcareapp.domain

data class ValidationResult(
    val isSuccessful: Boolean,
    val errorMessage: String = ""
)