package nondas.pap.petcareapp.presentation.medicine

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.squaredem.composecalendar.ComposeCalendar
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.presentation.ValidatedField
import nondas.pap.petcareapp.presentation.component.AddVerticalSpace
import nondas.pap.petcareapp.presentation.component.Comments
import nondas.pap.petcareapp.presentation.component.MyDropdown
import nondas.pap.petcareapp.presentation.component.MyText
import nondas.pap.petcareapp.presentation.component.MyTitle
import nondas.pap.petcareapp.presentation.component.PrimaryButton
import nondas.pap.petcareapp.presentation.component.SecondaryButton
import nondas.pap.petcareapp.presentation.component.inputText.InputText
import nondas.pap.petcareapp.presentation.pet.PetEvent
import nondas.pap.petcareapp.presentation.util.DateTransformation
import java.time.LocalDate
import java.util.Calendar

@Composable
fun AddMedicineScreen(
    viewModel: MedicineViewModel = hiltViewModel(),
    navController: NavController,
) {


    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AddMedicineContent(
        petName = state.petName,
        types = state.types,
        type = state.type,
        onTypeSelected = { viewModel.add(MedicineEvent.TypeSelected(it)) },
        date = state.date,
        onDatePerformedEntered = { viewModel.add(MedicineEvent.DatePerformedEntered(it)) } ,
        frequencyValues = state.frequencyValues,
        frequency = state.frequency,
        onFrequencySelected = { viewModel.add(MedicineEvent.FrequencySelected(it)) },
        comments = state.comments,
        onCommentsEntered = { viewModel.add(MedicineEvent.CommentsEntered(it)) },
        onAddMedicine = { viewModel.add(MedicineEvent.AddMedicine) },
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
            .background(colorResource(id = R.color.mpez))
    ) {

        AddVerticalSpace(50)


        MyTitle(title = "$petName medicine")


        AddVerticalSpace(20)


        MyDropdown(
            labelTitle = "Type",
            items = types,
            selectedItem = type,
            onItemSelected = { onTypeSelected(it) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        AddVerticalSpace(15)

        InputText(
            inputValue = date.value,
            valueEntered = { onDatePerformedEntered(it) },
            labelTitle = "Date performed",
            placeholder = "dd/MM/yyyy",
            isValidationSuccessful = date.validation.isSuccessful,
            errorMessage = date.validation.errorMessage,
            visualTransformation = DateTransformation()
        )




        AddVerticalSpace(15)

        MyDropdown(
            labelTitle = "Repeat when",
            items = frequencyValues,
            selectedItem = frequency,
            onItemSelected = { onFrequencySelected(it) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        AddVerticalSpace(15)


        Comments(
            inputValue = comments,
            valueEntered = { onCommentsEntered(it) }
        )


        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            buttonTitle = "save",
            onButtonClicked = { onAddMedicine() },
            hasBorder = false
        )

        AddVerticalSpace()

        SecondaryButton(
            buttonTitle = "cancel",
            onButtonClicked = { onCancelClicked() },
            hasBorder = false
        )


        AddVerticalSpace(20)

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
