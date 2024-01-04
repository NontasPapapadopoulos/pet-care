package nondas.pap.petcareapp.presentation.pet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.presentation.ValidatedField
import nondas.pap.petcareapp.presentation.component.AddVerticalSpace
import nondas.pap.petcareapp.presentation.component.MyDropdown
import nondas.pap.petcareapp.presentation.component.MyTitle
import nondas.pap.petcareapp.presentation.component.PrimaryButton
import nondas.pap.petcareapp.presentation.component.SecondaryButton
import nondas.pap.petcareapp.presentation.component.inputText.InputText


@Composable
fun EditPetScreen(
    viewModel: PetViewModel = hiltViewModel(),
    navController: NavController,
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()


    EditPetContent(
        name = state.name,
        dob = state.dob,
        type = state.type,
        petTypes = state.petTypes,
        breed = state.breed,
        isButtonEnabled = state.isAddButtonEnabled,
        onNameEntered = { viewModel.add(PetEvent.NameEntered(it)) },
        onDobEntered = { viewModel.add(PetEvent.DobEntered(it)) },
        onTypeSelected = { viewModel.add(PetEvent.TypeSelected(it)) },
        onBreedEntered = { viewModel.add(PetEvent.BreedEntered(it)) },
        onEditPet = { viewModel.add(PetEvent.EditPet) },
        onCancelButtonClicked = { navController.popBackStack() }
    )
}

@Composable
private fun EditPetContent(
    name: ValidatedField,
    dob: ValidatedField,
    type: String,
    petTypes: List<String>,
    breed: ValidatedField,
    isButtonEnabled: Boolean,
    onNameEntered: (String) -> Unit,
    onDobEntered: (String) -> Unit,
    onTypeSelected: (String) -> Unit,
    onBreedEntered: (String) -> Unit,
    onEditPet: () -> Unit,
    onCancelButtonClicked: () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.mpez))
    ) {

        AddVerticalSpace(50)

        MyTitle(title = "Edit Pet")


        AddVerticalSpace(30)

        InputText(
            labelTitle = "Name",
            placeholder = "Roza xx",
            inputValue = name.value,
            valueEntered = { onNameEntered(it) },
            isValidationSuccessful = name.validation.isSuccessful,
            errorMessage = name.validation.errorMessage
        )

        AddVerticalSpace(15)


        InputText(
            inputValue = dob.value,
            valueEntered = { onDobEntered(it) },
            labelTitle = "Date of birth",
            placeholder = "dd/MM/yyyy",
            isValidationSuccessful = dob.validation.isSuccessful,
            errorMessage = dob.validation.errorMessage,
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


        InputText(
            inputValue = breed.value,
            valueEntered = { onBreedEntered(it) },
            labelTitle = "Breed",
            placeholder = "French bulldog",
            isValidationSuccessful = breed.validation.isSuccessful,
            errorMessage = breed.validation.errorMessage
        )


        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            buttonTitle = "update",
            onButtonClicked = { onEditPet() },
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
private fun EditPetScreenPreview() {
    EditPetContent(
        name = ValidatedField(),
        dob = ValidatedField(),
        type = "",
        petTypes = listOf(),
        breed = ValidatedField(),
        isButtonEnabled = true,
        onNameEntered = {},
        onDobEntered = {},
        onTypeSelected = {},
        onBreedEntered = {},
        onEditPet = { /*TODO*/ },
        onCancelButtonClicked = {}
    )
}