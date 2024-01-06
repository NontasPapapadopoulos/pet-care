package nondas.pap.petcareapp.presentation.pet.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.presentation.ValidatedField
import nondas.pap.petcareapp.presentation.component.AddVerticalSpace
import nondas.pap.petcareapp.presentation.component.MyDropdown
import nondas.pap.petcareapp.presentation.component.MyTitle
import nondas.pap.petcareapp.presentation.component.OutLinedInputText
import nondas.pap.petcareapp.presentation.component.PrimaryButton
import nondas.pap.petcareapp.presentation.component.SecondaryButton
import nondas.pap.petcareapp.presentation.pet.add.AddPetEvent
import nondas.pap.petcareapp.presentation.pet.add.AddPetViewModel
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

        AddVerticalSpace(50)

        Text(
            text ="Add New Pet",
            style = MaterialTheme.typography.displayMedium
        )

        AddVerticalSpace(30)

        OutLinedInputText(
            label = "Name",
            inputValue = name.value,
            valueEntered = { onNameEntered(it) },
            validationResult = name.validation,
        )

        AddVerticalSpace(15)


        OutLinedInputText(
            inputValue = dob.value,
            valueEntered = { onDobEntered(it) },
            label = "Date of birth",
            validationResult = dob.validation,
            visualTransformation = DateTransformation()
        )

        AddVerticalSpace(15)


        MyDropdown(
            labelTitle = "Type",
            items = petTypes,
            selectedItem = type,
            onItemSelected = { onTypeSelected(it) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        AddVerticalSpace(15)


        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            buttonTitle = "add",
            onButtonClicked = { onAddPetClicked() },
            isEnabled = isButtonEnabled,
            backgroundColor = R.color.pink,
            textColor = R.color.white,
            hasBorder = false,
        )

        AddVerticalSpace()

        SecondaryButton(
            buttonTitle = "cancel",
            onButtonClicked = { onCancelButtonClicked() },
            backgroundColor = R.color.oil_green,
            textColor = R.color.white,
            hasBorder = false,
        )

        AddVerticalSpace(20)

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