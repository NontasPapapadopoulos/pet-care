package nondas.pap.petcareapp.presentation.medicine

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.domain.entity.Medicine
import nondas.pap.petcareapp.domain.entity.TimePeriod
import nondas.pap.petcareapp.infastracture.navigation.screen.MedicineScreen
import nondas.pap.petcareapp.presentation.component.AddHorizontalSpace
import nondas.pap.petcareapp.presentation.component.AddVerticalSpace
import nondas.pap.petcareapp.presentation.component.EditDeleteButtons
import nondas.pap.petcareapp.presentation.component.GreyBackground
import nondas.pap.petcareapp.presentation.component.MyImage
import nondas.pap.petcareapp.presentation.component.MyText
import nondas.pap.petcareapp.presentation.component.WarningDialog
import java.util.Date


@Composable
fun PetMedicineScreen(
    viewModel: MedicineViewModel = hiltViewModel(),
    navController: NavController
) {

    val state by viewModel.uiState.collectAsState()

    PetMedicineContent(
        selectedMedicine = state.selectedMedicine,
        onDeleteButtonClicked = { viewModel.add(MedicineEvent.DeleteButtonClicked(state.selectedMedicine)) },
        onEditButtonClicked = { viewModel.add(MedicineEvent.EditButtonClicked(state.selectedMedicine)) },
        onAddNewMedicineButtonClicked = { navController.navigate(route = MedicineScreen.AddMedicine.route) }
    )
}


@Composable
private fun PetMedicineContent(
    selectedMedicine: Medicine,
    onDeleteButtonClicked: (Medicine) -> Unit,
    onEditButtonClicked: (Medicine) -> Unit,
    onAddNewMedicineButtonClicked: () -> Unit
) {
    val showDialog = remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        val (column, dialog) = createRefs()

        Column(
            modifier = Modifier.constrainAs(column) {
                top.linkTo(parent.top)
            }

        ) {

            AddVerticalSpace(50)


            Text(
                text = "Pet name medicine",
                style = MaterialTheme.typography.displayMedium
            )

            AddVerticalSpace(30)


            MedicineItem(
                medicine = Medicine(
                    type = "Vaccine",
                    dateReceived = Date(),
                    repeatRate = TimePeriod.EVERY_YEAR,
                    comments = "xxx"
                )
            )

            Spacer(modifier = Modifier.weight(1f))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                MyImage(
                    imageId = R.drawable.baseline_add_circle_24_blue,
                    modifier = Modifier
                        .size(60.dp)
                        .clickable { onAddNewMedicineButtonClicked() }
                )
            }
        }


        GreyBackground(isVisible = showDialog.value)
        if (showDialog.value) {


            WarningDialog(
                title = "Delete ${selectedMedicine.type}",
                primaryButtonText = "delete",
                secondaryButtonText = "cancel",
                onPrimaryButtonClicked = { onDeleteButtonClicked(selectedMedicine) },
                onDismiss = { showDialog.value = false },
                onSecondaryButtonClicked = { showDialog.value = false },
                modifier = Modifier.constrainAs(dialog) {
                    bottom.linkTo(parent.bottom)
                }
            )
        }

    }
}


@Composable
fun MedicineItem(medicine: Medicine) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp)
            .background(
                color = colorResource(id = R.color.light_blue),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        MyImage(
            imageId = R.drawable.ic_launcher_foreground,
            modifier = Modifier.size(40.dp)
        )

        AddHorizontalSpace(15)

        Column {

            Row(
                modifier = Modifier
                            .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                MyText(
                    text = medicine.type,
                    fillMaxWidth = false,
                    color = R.color.white
                )

                EditDeleteButtons(
                    onEditButtonClicked = {},
                    onDeleteButtonClicked = {}
                )
            }

            MyText(
                text = medicine.dateReceived.toString(),
                fillMaxWidth = false,
                color = R.color.white
            )
            MyText(
                text = medicine.repeatRate.value,
                fillMaxWidth = false,
                color = R.color.white
            )
            MyText(
                text = medicine.comments,
                fillMaxWidth = false,
                color = R.color.white
            )

        }

    }
}


@Preview
@Composable
private fun MedicineScreenPreview() {
    PetMedicineContent(
        selectedMedicine = Medicine(),
        onDeleteButtonClicked = {},
        onEditButtonClicked = {},
        onAddNewMedicineButtonClicked = {}
    )
}