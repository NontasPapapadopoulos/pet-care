package nondas.pap.petcareapp.domain.usecase.validator

data class ValidationResult(
    val isError: Boolean,
    val errorMessage: String = ""
)