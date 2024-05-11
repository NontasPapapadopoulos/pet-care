package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import nondas.pap.petcareapp.domain.usecase.validator.ValidationResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLinedInputText(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    validationResult: ValidationResult = ValidationResult(isError = true),
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        isError = !validationResult.isError,
        supportingText = {
            if (!validationResult.isError)
                Text(text = validationResult.errorMessage)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorSupportingTextColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.error
        ),
        visualTransformation = visualTransformation,
        modifier = Modifier.fillMaxWidth()

    )

}