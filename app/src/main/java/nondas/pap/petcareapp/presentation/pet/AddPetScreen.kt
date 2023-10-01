package nondas.pap.petcareapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.domain.model.Pet
import nondas.pap.petcareapp.presentation.component.AddVerticalSpace
import nondas.pap.petcareapp.presentation.component.MyDropdown
import nondas.pap.petcareapp.presentation.component.MyTitle
import nondas.pap.petcareapp.presentation.component.PrimaryButton
import nondas.pap.petcareapp.presentation.component.SecondaryButton
import nondas.pap.petcareapp.presentation.component.inputText.InputText
import nondas.pap.petcareapp.presentation.pet.PetEvent
import nondas.pap.petcareapp.presentation.pet.PetViewModel


@Composable
fun AddPetScreen(
    navController: NavController
) {

    val viewModel: PetViewModel = hiltViewModel()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.mpez))
    ) {

        AddVerticalSpace(50)

        MyTitle(title = "Add New Pet")


        AddVerticalSpace(30)

        InputText(
            placeholder = "your pets name",
            inputValue = viewModel.state.name,
            valueEntered = { viewModel.onEvent(PetEvent.NameEntered(it)) },
            isValidationSuccessful = viewModel.state.nameValidation.isSuccessful,
            errorMessage = viewModel.state.nameValidation.errorMessage
        )

        AddVerticalSpace(15)




        if (viewModel.state.isAboveOneYearOld)
            InputText(
                inputValue = viewModel.state.age,
                valueEntered = { viewModel.onEvent(PetEvent.AgeEntered(it)) }
            )

        else
            MyDropdown(
                selectedItem = viewModel.state.age,
                onItemSelected = { viewModel.onEvent(PetEvent.AgeEntered(it)) }
            )

        AddVerticalSpace(15)


        MyDropdown(
            selectedItem = viewModel.state.type,
            onItemSelected = { viewModel.onEvent(PetEvent.TypeEntered(it)) }
        )

        AddVerticalSpace(15)

        InputText(
            inputValue = viewModel.state.type,
            valueEntered = { viewModel.onEvent(PetEvent.BreedEntered(it)) }
        )

        AddVerticalSpace(15)

        InputText(
            inputValue = viewModel.state.breed,
            valueEntered = { viewModel.onEvent(PetEvent.BreedEntered(it)) }
        )


        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            buttonTitle = "add",
            onButtonClicked = { viewModel.onEvent(PetEvent.AddPet) },
            isEnabled = viewModel.state.isAddButtonEnabled
        )

        AddVerticalSpace()

        SecondaryButton(
            buttonTitle = "cancel",
            onButtonClicked = { navController.popBackStack() }
        )

        AddVerticalSpace(20)



    }


}




@Preview
@Composable
private fun AddPetScreenPreview() {
    AddPetScreen(navController = rememberNavController())

}