package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun RadioButtonPair(
    options: List<String>,
    selectedOption: Int = 0,
    onOptionSelected: (Int) -> Unit
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {

        options.forEachIndexed { index, s ->
            RadioGroupOption(
                text = options[index],
                index = index,
                selectedOption = selectedOption,
                onOptionSelected = { onOptionSelected(it) }
            )
        }
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

    Column(
        modifier = Modifier
            .padding(20.dp, 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        MyText(
            text = text,
            fillMaxWidth = false,
            modifier = Modifier
                .clickable { onOptionSelected(index) }
                .width(30.dp)

        )

        RadioButton(
            selected = isSelected,
            onClick = { onOptionSelected(index) },

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
    RadioButtonPair(
        options = listOf("Yes", "No"),
        selectedOption = 0,
        onOptionSelected =  {}
    )
}