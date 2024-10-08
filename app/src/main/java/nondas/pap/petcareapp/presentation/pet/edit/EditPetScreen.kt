package nondas.pap.petcareapp.presentation.pet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import nondas.pap.petcareapp.presentation.component.LoadingBox
import nondas.pap.petcareapp.presentation.component.VerticalSpace

import nondas.pap.petcareapp.presentation.pet.edit.EditPetEvent
import nondas.pap.petcareapp.presentation.pet.edit.EditPetState
import nondas.pap.petcareapp.presentation.pet.edit.EditPetViewModel
import nondas.pap.petcareapp.presentation.util.DateTransformation


@Composable
fun EditPetScreen(
    viewModel: EditPetViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(val state = uiState) {
        is EditPetState.Content -> {
            EditPetContent(
                name = state.name,
                dob = state.dob,
                type = state.kind,
                isButtonEnabled = state.isAddButtonEnabled,
                onNameEntered = { viewModel.add(EditPetEvent.NameEntered(it)) },
                onDobEntered = { viewModel.add(EditPetEvent.DobEntered(it)) },
                onTypeSelected = { viewModel.add(EditPetEvent.TypeSelected(it)) },
                onCancelButtonClicked = { onNavigateBack() },
                onEditPet = { }
            )
        }
        EditPetState.Idle -> {
            LoadingBox()
        }
    }


}

@Composable
private fun EditPetContent(
    name: ValidatedField,
    dob: ValidatedField,
    type: String,
    isButtonEnabled: Boolean,
    onNameEntered: (String) -> Unit,
    onDobEntered: (String) -> Unit,
    onTypeSelected: (String) -> Unit,
    onEditPet: () -> Unit,
    onCancelButtonClicked: () -> Unit

) {

    Scaffold {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            VerticalSpace(50)


            Text(
                text ="Edit Pet",
                style = MaterialTheme.typography.displayMedium
            )

            VerticalSpace(30)


            OutlinedTextField(
                value = name.value,
                onValueChange = { onNameEntered(it) },
                label =  { Text(text = "Name") },
                isError = name.validation.isError,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )


            VerticalSpace(15)

            OutlinedTextField(
                value = dob.value,
                onValueChange = { onDobEntered(it) },
                label =  { Text(text = "Date of birth") },
                isError = dob.validation.isError,
                singleLine = true,
                visualTransformation = DateTransformation(),
                modifier = Modifier.fillMaxWidth()
            )


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
                onClick = { onEditPet() },
                enabled = isButtonEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Update")
            }

            VerticalSpace()


            Button(
                onClick = { onCancelButtonClicked() },
                enabled = isButtonEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Cancel")
            }

        }

    }
}


@Preview
@Composable
private fun EditPetScreenPreview() {
    EditPetContent(
        name = ValidatedField(),
        dob = ValidatedField(),
        type = "",
        isButtonEnabled = true,
        onNameEntered = {},
        onDobEntered = {},
        onTypeSelected = {},
        onEditPet = { /*TODO*/ },
        onCancelButtonClicked = {}
    )
}