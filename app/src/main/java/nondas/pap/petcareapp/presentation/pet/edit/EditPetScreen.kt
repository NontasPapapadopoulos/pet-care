package nondas.pap.petcareapp.presentation.pet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import nondas.pap.petcareapp.presentation.component.MyDropdown
import nondas.pap.petcareapp.presentation.component.OutLinedInputText
import nondas.pap.petcareapp.presentation.component.PrimaryButton
import nondas.pap.petcareapp.presentation.component.SecondaryButton
import nondas.pap.petcareapp.presentation.pet.edit.EditPetEvent
import nondas.pap.petcareapp.presentation.pet.edit.EditPetViewModel


@Composable
fun EditPetScreen(
    viewModel: EditPetViewModel = hiltViewModel(),
    navController: NavController,
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()


    EditPetContent(
        name = state.name,
        dob = state.dob,
        type = state.type,
        petTypes = state.petTypes,
        isButtonEnabled = state.isAddButtonEnabled,
        onNameEntered = { viewModel.add(EditPetEvent.NameEntered(it)) },
        onDobEntered = { viewModel.add(EditPetEvent.DobEntered(it)) },
        onTypeSelected = { viewModel.add(EditPetEvent.TypeSelected(it)) },
        onCancelButtonClicked = { navController.popBackStack() },
        onEditPet = { viewModel.add(EditPetEvent.EditButtonClicked(state.selectedPetDomainEntity)) }
    )
}

@Composable
private fun EditPetContent(
    name: ValidatedField,
    dob: ValidatedField,
    type: String,
    petTypes: List<String>,
    isButtonEnabled: Boolean,
    onNameEntered: (String) -> Unit,
    onDobEntered: (String) -> Unit,
    onTypeSelected: (String) -> Unit,
    onEditPet: () -> Unit,
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
            text ="Edit Pet",
            style = MaterialTheme.typography.displayMedium
        )

        VerticalSpace(30)

        OutLinedInputText(
            label = "Name",
            inputValue = name.value,
            valueEntered = { onNameEntered(it) },
            validationResult = name.validation
        )

        VerticalSpace(15)


        OutLinedInputText(
            inputValue = dob.value,
            valueEntered = { onDobEntered(it) },
            label = "Date of birth",
            placeholder = "dd/MM/yyyy",
            validationResult = dob.validation
        )

        VerticalSpace(15)


        MyDropdown(
            labelTitle = "Type",
            items = petTypes,
            selectedItem = type,
            onItemSelected = { onTypeSelected(it) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        VerticalSpace(15)


        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            buttonTitle = "update",
            onButtonClicked = { onEditPet() },
            isEnabled = isButtonEnabled,
            backgroundColor = R.color.pink,
            textColor = R.color.white,
            hasBorder = false,
        )

        VerticalSpace()

        SecondaryButton(
            buttonTitle = "cancel",
            onButtonClicked = { onCancelButtonClicked() },
            backgroundColor = R.color.oil_green,
            textColor = R.color.white,
            hasBorder = false,
        )

        VerticalSpace(20)

    }
}


@Preview
@Composable
private fun EditPetScreenPreview() {
    EditPetContent(
        name = ValidatedField(),
        dob = ValidatedField(),
        type = "",
        petTypes = listOf(),
        isButtonEnabled = true,
        onNameEntered = {},
        onDobEntered = {},
        onTypeSelected = {},
        onEditPet = { /*TODO*/ },
        onCancelButtonClicked = {}
    )
}