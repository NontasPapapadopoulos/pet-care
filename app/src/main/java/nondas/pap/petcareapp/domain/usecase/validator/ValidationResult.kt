package nondas.pap.petcareapp.domain.usecase.validator

data class ValidationResult(
    val isSuccessful: Boolean,
    val errorMessage: String = ""
)