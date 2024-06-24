package nondas.pap.petcareapp.presentation.medicine.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

import nondas.pap.petcareapp.presentation.ValidatedField
import nondas.pap.petcareapp.presentation.component.VerticalSpace


@Composable
fun AddMedicineScreen(
    viewModel: AddMedicineViewModel = hiltViewModel(),
    navController: NavController,
) {


    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AddMedicineContent(
        petName = state.petName,
        types = state.types,
        type = state.type,
        onTypeSelected = { viewModel.add(AddMedicineEvent.TypeSelected(it)) },
        date = state.date,
        onDatePerformedEntered = { viewModel.add(AddMedicineEvent.DatePerformedEntered(it)) } ,
        frequencyValues = state.frequencyValues,
        frequency = state.frequency,
        onFrequencySelected = { viewModel.add(AddMedicineEvent.FrequencySelected(it)) },
        comments = state.comments,
        onCommentsEntered = { viewModel.add(AddMedicineEvent.CommentsEntered(it)) },
        onAddMedicine = { viewModel.add(AddMedicineEvent.AddMedicine) },
        onCancelClicked = { navController.popBackStack() }
    )

}

@Composable
private fun AddMedicineContent(
    petName: String,
    types: List<String>,
    type: String,
    onTypeSelected: (String) -> Unit,
    date: ValidatedField,
    onDatePerformedEntered: (String) -> Unit,
    frequencyValues: List<String>,
    frequency: String,
    onFrequencySelected: (String) -> Unit,
    comments: String,
    onCommentsEntered: (String) -> Unit,
    onAddMedicine: () -> Unit,
    onCancelClicked: () -> Unit

) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        VerticalSpace(50)

        Text(
            text = "$petName medicine",
            style = MaterialTheme.typography.displayMedium
        )

        VerticalSpace(20)

//        MyDropdown(
//            labelTitle = "Type",
//            items = types,
//            selectedItem = type,
//            onItemSelected = { onTypeSelected(it) },
//            modifier = Modifier.padding(20.dp, 0.dp)
//        )

        VerticalSpace(15)

        OutlinedTextField(
            value = date.value,
            onValueChange = {onDatePerformedEntered(it)},
            label = { Text(text = "Date performed") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )


        VerticalSpace(15)

//        MyDropdown(
//            labelTitle = "Repeat when",
//            items = frequencyValues,
//            selectedItem = frequency,
//            onItemSelected = { onFrequencySelected(it) },
//            modifier = Modifier.padding(20.dp, 0.dp)
//        )

        VerticalSpace(15)



        OutlinedTextField(
            value = comments,
            onValueChange = { onCommentsEntered(it)},
            label = { Text(text = "Comments") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onAddMedicine() },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Save")
        }

        VerticalSpace()

        Button(
            onClick = { onCancelClicked() },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Cancel")
        }

        VerticalSpace(20)

    }
}



@Preview
@Composable
private fun AddMedicineContentPreview() {
    AddMedicineContent(
        petName = "xx",
        types = listOf(),
        type = "xxx",
        onTypeSelected = {},
        date = ValidatedField(value = "12/12/2024"),
        onDatePerformedEntered = {},
        frequencyValues = listOf(),
        frequency = "asads",
        onFrequencySelected = {},
        comments = "|asdas",
        onCommentsEntered = {},
        onAddMedicine = {},
        onCancelClicked = {}
    )
}
