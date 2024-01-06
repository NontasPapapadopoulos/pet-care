package nondas.pap.petcareapp.presentation.component

import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun OutlinedDropdown(
    options: List<String>,
    isExpanded: Boolean,
    selectedOption: String,
    label: String,
    onOptionSelected: (String) -> Unit,
    onExpandChange: () -> Unit
) {

//    ExposedDropdownMenuBox(
//        expanded = isExpanded,
//        onExpandedChange = { onExpandChange() }
//    ) {
//
//        OutlinedTextField(
//            inputValue = selectedOption,
//            valueEntered = { onOptionSelected(selectedOption) },
//            label = {
//                    Text(text = label)
//            },
//            readOnly = true,
//
//        )
//
//
//
//    }
}