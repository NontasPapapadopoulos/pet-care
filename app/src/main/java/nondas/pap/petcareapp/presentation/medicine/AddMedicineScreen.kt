package nondas.pap.petcareapp.presentation.medicine

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
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
import java.time.LocalDate

@Composable
fun AddMedicineScreen(
    navController: NavController,
    state: MedicineState,
    onEvent: (MedicineEvent) -> Unit
) {

    val showDialog = rememberSaveable { mutableStateOf(false) }

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
            onItemSelected = { onEvent(MedicineEvent.TypeSelected(it)) },
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        AddVerticalSpace(15)

        MyText(
            text = "Date performed",
            textAlignment = TextAlign.Start,
            fillMaxWidth = false,
            modifier = Modifier.padding(start = 20.dp)
        )

        AddVerticalSpace(6)

        MyText(
            text = state.date,
            textAlignment = TextAlign.Start,
            modifier = Modifier
                .padding(20.dp, 0.dp)
                .height(46.dp)
                .background(
                    color = colorResource(R.color.white),
                    shape = RoundedCornerShape(size = 36.dp)
                )
                .border(
                    width = 1.dp,
                    color = colorResource(R.color.grey),
                    shape = RoundedCornerShape(size = 36.dp)
                )
                .padding(14.dp, 0.dp)
                .clickable { showDialog.value = true }
        )

        if (showDialog.value) {
            ComposeCalendar(
                onDone = { it: LocalDate ->
                    // Hide dialog
                    showDialog.value = false
                    // Do something with the date
                },
                onDismiss = {
                    // Hide dialog
                    showDialog.value = false
                }
            )
        }

        AddVerticalSpace(15)

        MyDropdown(
            labelTitle = "Repeat when",
            items = state.frequencyValues,
            selectedItem = state.frequency,
            onItemSelected = { onEvent(MedicineEvent.FrequencySelected(it)) },
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