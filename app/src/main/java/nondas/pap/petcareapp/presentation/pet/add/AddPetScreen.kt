package nondas.pap.petcareapp.presentation.pet.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nondas.pap.petcareapp.presentation.ValidatedField
import nondas.pap.petcareapp.presentation.component.LoadingBox
import nondas.pap.petcareapp.presentation.component.VerticalSpace
import nondas.pap.petcareapp.presentation.util.DateTransformation
import nondas.pap.petcareapp.ui.theme.PetCareAppTheme


@Composable
fun AddPetScreen(
    viewModel: AddPetViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    when(val state = uiState) {
        is AddPetsState.Content -> {
            AddPetContent(
                content = state,
                onNameEntered = { viewModel.add(AddPetEvent.NameEntered(it)) },
                onDobEntered = { viewModel.add(AddPetEvent.DobEntered(it)) },
                onTypeSelected = { viewModel.add(AddPetEvent.KindSelected(it)) },
                onAddPetClicked = { viewModel.add(AddPetEvent.AddPet) },
                onCancelButtonClicked = { onNavigateBack() }
            )
        }
        AddPetsState.Idle -> {
            LoadingBox()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddPetContent(
    content: AddPetsState.Content,
    onNameEntered: (String) -> Unit,
    onDobEntered: (String) -> Unit,
    onTypeSelected: (String) -> Unit,
    onAddPetClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit
) {

    Scaffold {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            VerticalSpace(50)

            Text(
                text ="Add New Pet",
                style = MaterialTheme.typography.displayMedium
            )

            VerticalSpace(30)

            OutlinedTextField(
                value = content.name.value,
                onValueChange = { onNameEntered(it) },
                label =  { Text(text = "Name") },
                isError = content.name.validation.isError,
                singleLine = true
            )


            VerticalSpace(15)

            OutlinedTextField(
                value = content.dob.value,
                onValueChange = { onDobEntered(it) },
                label =  { Text(text = "Date of birth") },
                isError = content.dob.validation.isError,
                singleLine = true,
                visualTransformation = DateTransformation()
            )

            VerticalSpace(15)

            var expanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded } ) {
                OutlinedTextField(
                    value = content.kind,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor()

                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    content.petTypes.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                onTypeSelected(item)
                                expanded = false
                            }
                        )
                    }
                }
            }


            VerticalSpace(15)


            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onAddPetClicked() },
                enabled = content.isAddButtonEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)

            ) {
                Text(text = "Add")
            }

            VerticalSpace()


            Button(
                onClick = { onCancelButtonClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(text = "Cancel")
            }


            VerticalSpace(20)
        }

    }
}


@Preview
@Composable
private fun AddPetScreenPreview() {
    PetCareAppTheme {
        AddPetContent(
                content = AddPetsState.Content(
                name = ValidatedField("xx"),
                dob = ValidatedField("12/12/2022"),
                kind = "cat", isAddButtonEnabled = true,
                petTypes = listOf()),
            onNameEntered = {},
            onDobEntered = {},
            onTypeSelected = {},
            onAddPetClicked = { /*TODO*/ },
            onCancelButtonClicked = {}
        )
    }
}