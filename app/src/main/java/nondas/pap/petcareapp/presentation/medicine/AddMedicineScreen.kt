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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.squaredem.composecalendar.ComposeCalendar
import nondas.pap.petcareapp.R
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
    navController: NavController,
    state: MedicineState,
    onEvent: (MedicineEvent) -> Unit
) {


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
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
            onItemSelected = { onEvent(MedicineEvent.TypeSelected(it)) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        AddVerticalSpace(15)

        InputText(
            inputValue = state.date,
            valueEntered = { onEvent(MedicineEvent.DatePerformedEntered(it)) },
            labelTitle = "Date performed",
            placeholder = "dd/MM/yyyy",
            isValidationSuccessful = state.dateValidation.isSuccessful,
            errorMessage = state.dateValidation.errorMessage,
            visualTransformation = DateTransformation()
        )




        AddVerticalSpace(15)

        MyDropdown(
            labelTitle = "Repeat when",
            items = state.frequencyValues,
            selectedItem = state.frequency,
            onItemSelected = { onEvent(MedicineEvent.FrequencySelected(it)) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        AddVerticalSpace(15)


        Comments(
            inputValue = state.comments,
            valueEntered = { onEvent(MedicineEvent.CommentsEntered(it)) }
        )


        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            buttonTitle = "save",
            onButtonClicked = { onEvent(MedicineEvent.AddMedicine) },
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
    AddMedicineScreen(
        navController = rememberNavController(),
        state = MedicineState(),
        onEvent = {}
    )
}

//        MyText(
//            text = "Date performed",
//            textAlignment = TextAlign.Start,
//            fillMaxWidth = false,
//            modifier = Modifier.padding(start = 20.dp)
//        )
//
//        val calendar = Calendar.getInstance()
//
//
//        // Fetching current year, month and day
//        val year = calendar[Calendar.YEAR]
//        val month = calendar[Calendar.MONTH]
//        val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
//
//        val datePicker = DatePickerDialog(
//            context,
//            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
//              onEvent(MedicineEvent.SetDate("$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"))
//            }, year, month, dayOfMonth
//        )
//
//        AddVerticalSpace(6)
//
//        MyText(
//            text = state.date,
//            textAlignment = TextAlign.Start,
//            modifier = Modifier
//                .padding(20.dp, 0.dp)
//                .height(46.dp)
//                .background(
//                    color = colorResource(R.color.white),
//                    shape = RoundedCornerShape(size = 36.dp)
//                )
//                .border(
//                    width = 1.dp,
//                    color = colorResource(R.color.grey),
//                    shape = RoundedCornerShape(size = 36.dp)
//                )
//                .padding(start = 14.dp, top = 5.dp, bottom = 5.dp)
//                .clickable {  datePicker.show() }
//        )