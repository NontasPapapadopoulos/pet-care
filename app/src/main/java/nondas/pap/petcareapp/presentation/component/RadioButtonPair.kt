package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun RadioButtonPair() {
    var selectedOption by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        RadioGroupOption(
            text = "Option 1",
            index = 0,
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
        )

        RadioGroupOption(
            text = "Option 2",
            index = 1,
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
        )
    }
}

@Composable
fun RadioGroupOption(
    text: String,
    index: Int,
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit
) {
    val isSelected = index == selectedOption

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onOptionSelected(index) },
            modifier = Modifier.padding(end = 16.dp)
        )

        Text(
            text = text,
            modifier = Modifier
                .clickable { onOptionSelected(index) }
                .padding(start = 4.dp)
        )
    }
}

@Composable
fun RadioButtonExample() {
    var selectedOption by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        RadioGroupOption(
            text = "Option 1",
            index = 0,
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
        )

        RadioGroupOption(
            text = "Option 2",
            index = 1,
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
        )

        Text(
            text = "Selected Option: $selectedOption",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview
@Composable
fun PreviewRadioButtonPair() {
    RadioButtonPair()
}