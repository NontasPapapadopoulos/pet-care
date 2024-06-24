package nondas.pap.petcareapp.presentation.pet.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.presentation.ValidatedField
import nondas.pap.petcareapp.presentation.component.VerticalSpace
import nondas.pap.petcareapp.presentation.util.DateTransformation


@Composable
fun AddPetScreen(
    viewModel: AddPetViewModel = hiltViewModel(),
    navController: NavController
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AddPetContent(
        name = state.name,
        dob = state.dob,
        type = state.type,
        petTypes = state.petTypes,
        isButtonEnabled = state.isAddButtonEnabled,
        onNameEntered = { viewModel.add(AddPetEvent.NameEntered(it)) },
        onDobEntered = { viewModel.add(AddPetEvent.DobEntered(it)) },
        onTypeSelected = { viewModel.add(AddPetEvent.TypeSelected(it)) },
        onAddPetClicked = { viewModel.add(AddPetEvent.AddPet) },
        onCancelButtonClicked = { navController.popBackStack() }
    )

}

@Composable
private fun AddPetContent(
    name: ValidatedField,
    dob: ValidatedField,
    type: String,
    petTypes: List<String>,
    isButtonEnabled: Boolean,
    onNameEntered: (String) -> Unit,
    onDobEntered: (String) -> Unit,
    onTypeSelected: (String) -> Unit,
    onAddPetClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        VerticalSpace(50)

        Text(
            text ="Add New Pet",
            style = MaterialTheme.typography.displayMedium
        )

        VerticalSpace(30)

        OutlinedTextField(
            value = name.value,
            onValueChange = { onNameEntered(it) },
            label =  { Text(text = "Name") },
            isError = name.validation.isError,
            singleLine = true
        )


        VerticalSpace(15)

        OutlinedTextField(
            value = dob.value,
            onValueChange = { onDobEntered(it) },
            label =  { Text(text = "Date of birth") },
            isError = dob.validation.isError,
            singleLine = true,
            visualTransformation = DateTransformation()
        )



        VerticalSpace(15)


//        MyDropdown(
//            labelTitle = "Type",
//            items = petTypes,
//            selectedItem = type,
//            onItemSelected = { onTypeSelected(it) },
//            modifier = Modifier.padding(20.dp, 0.dp)
//        )

        VerticalSpace(15)


        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onAddPetClicked() },
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)

        ) {
            Text(text = "Add")
        }

        VerticalSpace()


        Button(
            onClick = { onCancelButtonClicked() },
            enabled = isButtonEnabled,
        ) {
            Text(text = "Cancel")
        }




        VerticalSpace(20)

    }
}




@Preview
@Composable
private fun AddPetScreenPreview() {
    AddPetContent(
        name = ValidatedField("xx"),
        dob = ValidatedField("12/12/2022"),
        type = "cat",
        petTypes = listOf(),
        isButtonEnabled = true,
        onNameEntered = {},
        onDobEntered = {},
        onTypeSelected = {},
        onAddPetClicked = { /*TODO*/ },
        onCancelButtonClicked = {}
    )

}