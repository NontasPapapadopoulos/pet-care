package nondas.pap.petcareapp.presentation.component

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.VisualTransformation
import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLinedInputText(
    inputValue: String,
    valueEntered: (String) -> Unit,
    label: String,
    placeholder: String = "",
    validationResult: ValidationResult = ValidationResult(isSuccessful = true),
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    OutlinedTextField(
        value = inputValue,
        onValueChange = { valueEntered(it) },
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        isError = !validationResult.isSuccessful,
        supportingText = {
            if (!validationResult.isSuccessful)
                Text(text = validationResult.errorMessage)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorSupportingTextColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.error
        ),
        visualTransformation = visualTransformation

    )

}