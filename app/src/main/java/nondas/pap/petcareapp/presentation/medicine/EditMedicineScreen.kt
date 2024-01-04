package nondas.pap.petcareapp.presentation.medicine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.presentation.component.AddVerticalSpace
import nondas.pap.petcareapp.presentation.component.Comments
import nondas.pap.petcareapp.presentation.component.MyDropdown
import nondas.pap.petcareapp.presentation.component.MyText
import nondas.pap.petcareapp.presentation.component.MyTitle
import nondas.pap.petcareapp.presentation.component.PrimaryButton
import nondas.pap.petcareapp.presentation.component.SecondaryButton

@Composable
fun EditMedicineScreen(
    viewModel: MedicineViewModel = hiltViewModel(),
    navController: NavController,
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.mpez))
    ) {

        AddVerticalSpace(50)


        MyTitle(title = "${state.petName} medicine")


        AddVerticalSpace(20)


        MyDropdown(
            labelTitle = "Type",
            items = state.types,
            selectedItem = state.type,
            onItemSelected = { viewModel.add(MedicineEvent.TypeSelected(it)) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        AddVerticalSpace(15)

        MyDropdown(
            labelTitle = "Repeat when",
            items = state.frequencyValues,
            selectedItem = state.frequency,
            onItemSelected = { viewModel.add(MedicineEvent.FrequencySelected(it)) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )


        AddVerticalSpace(15)

        MyText(
            text = "Comments",
            textAlignment = TextAlign.Start,
            modifier = Modifier.padding(start = 20.dp)
        )
        AddVerticalSpace(6)

        Comments(
            inputValue = state.comments,
            valueEntered = { viewModel.add(MedicineEvent.CommentsEntered(it)) }
        )


        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            buttonTitle = "update",
            onButtonClicked = { viewModel.add(MedicineEvent.UpdateMedicine) },
            hasBorder = false
        )

        AddVerticalSpace()

        SecondaryButton(
            buttonTitle = "cancel",
            onButtonClicked = { navController.popBackStack() },
            hasBorder = false
        )


        AddVerticalSpace(20)



    }


}





@Preview
@Composable
private fun AddMedicineScreenPreview() {
    EditMedicineScreen(
        navController = rememberNavController(),

    )
}