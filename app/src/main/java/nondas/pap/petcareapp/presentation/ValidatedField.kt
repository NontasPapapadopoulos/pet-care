package nondas.pap.petcareapp.presentation

import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult


data class ValidatedField(val value: String, val validation: ValidationResult)