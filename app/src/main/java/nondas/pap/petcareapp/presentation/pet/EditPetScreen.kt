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
            inputValue = state.name,
            valueEntered = { viewModel.add(PetEvent.NameEntered(it)) },
            isValidationSuccessful = state.nameValidation.isSuccessful,
            errorMessage = state.nameValidation.errorMessage
        )

        AddVerticalSpace(15)


        InputText(
            inputValue = state.dob,
            valueEntered = { viewModel.add(PetEvent.DobEntered(it)) },
            labelTitle = "Date of birth",
            placeholder = "dd/MM/yyyy",
            isValidationSuccessful = state.dobValidation.isSuccessful,
            errorMessage = state.dobValidation.errorMessage,
        )

        AddVerticalSpace(15)


        MyDropdown(
            labelTitle = "Type",
            items = state.petTypes,
            selectedItem = state.type,
            onItemSelected = { viewModel.add(PetEvent.TypeSelected(it)) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        AddVerticalSpace(15)


        InputText(
            inputValue = state.breed,
            valueEntered = { viewModel.add(PetEvent.BreedEntered(it)) },
            labelTitle = "Breed",
            placeholder = "French bulldog",
            isValidationSuccessful = state.breedValidation.isSuccessful,
            errorMessage = state.breedValidation.errorMessage
        )


        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            buttonTitle = "update",
            onButtonClicked = { viewModel.add(PetEvent.EditPet) },
            isEnabled = state.isAddButtonEnabled,
            backgroundColor = R.color.pink,
            textColor = R.color.white,
            hasBorder = false,
        )

        AddVerticalSpace()

        SecondaryButton(
            buttonTitle = "cancel",
            onButtonClicked = { navController.popBackStack() },
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
    AddPetScreen(
        navController = rememberNavController()
    )
}