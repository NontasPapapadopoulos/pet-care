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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import nondas.pap.petcareapp.domain.entity.MedicineDomainEntity
import nondas.pap.petcareapp.domain.entity.TimePeriod
import nondas.pap.petcareapp.presentation.navigation.screen.MedicineScreen
import nondas.pap.petcareapp.presentation.component.AddHorizontalSpace
import nondas.pap.petcareapp.presentation.component.VerticalSpace
import nondas.pap.petcareapp.presentation.component.EditDeleteButtons
import nondas.pap.petcareapp.presentation.component.LoadingBox

import java.util.Date


@Composable
fun PetMedicineScreen(
    viewModel: MedicineViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState by viewModel.uiState.collectAsState()

    when(val state = uiState) {
        is MedicineState.Content -> {
            PetMedicineContent(
                content = state,
                onDeleteButtonClicked = { viewModel.add(MedicineEvent.DeleteButtonClicked(it)) },
                onEditButtonClicked = { viewModel.add(MedicineEvent.EditButtonClicked(it)) },
                onAddNewMedicineButtonClicked = { navController.navigate(route = MedicineScreen.AddMedicine.route) }
            )
        }
        MedicineState.Idle -> {
            LoadingBox()
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PetMedicineContent(
    content: MedicineState.Content,
    onDeleteButtonClicked: (MedicineDomainEntity) -> Unit,
    onEditButtonClicked: (MedicineDomainEntity) -> Unit,
    onAddNewMedicineButtonClicked: () -> Unit
) {
    val showDialog = remember { mutableStateOf(false) }

    Scaffold {

        Column(
            modifier = Modifier.padding(it)
        ) {

            VerticalSpace(50)


            Text(
                text = "Pet name medicine",
                style = MaterialTheme.typography.displayMedium
            )

            VerticalSpace(30)


            content.medicines.forEach { medicine ->
                MedicineItem(
                    medicine = medicine,
                    onEditButtonClicked = { onEditButtonClicked(medicine) },
                    onDeleteButtonClicked = { onDeleteButtonClicked(medicine) }
                )
            }

            Spacer(modifier = Modifier.weight(1f))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clickable { onAddNewMedicineButtonClicked() }
                )
            }

            val sheetState = rememberModalBottomSheetState()
            if (showDialog.value) {

                ModalBottomSheet(
                    onDismissRequest = { /*TODO*/ },
                    sheetState = sheetState
                ) {

                    Text(text = "Delete ${content.selectedMedicine!!.type}")

                    VerticalSpace(20)

                    Button(
                        onClick = { onDeleteButtonClicked(content.selectedMedicine!!) },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Delete")
                    }


                    VerticalSpace(10)


                    Button(
                        onClick = { showDialog.value = false },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Cancel")
                    }
                }

            }
        }

    }

}



@Composable
fun MedicineItem(
    medicine: MedicineDomainEntity,
    onEditButtonClicked: (MedicineDomainEntity) -> Unit,
    onDeleteButtonClicked: (MedicineDomainEntity) -> Unit
) {

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

        Icon(
            Icons.Default.MedicalServices,
            contentDescription = null,
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

                Text(
                    text = medicine.type,
                )

                EditDeleteButtons(
                    onEditButtonClicked = { onEditButtonClicked(medicine) },
                    onDeleteButtonClicked = { onDeleteButtonClicked(medicine)}
                )
            }

            Text(
                text = medicine.dateReceived.toString(),
            )
            Text(
                text = medicine.repeatRate,
            )
            Text(
                text = medicine.comments,
            )

        }

    }
}


@Preview
@Composable
private fun MedicineScreenPreview() {
    PetMedicineContent(
        content = MedicineState.Content(
            selectedMedicine = null,
            medicines = listOf(

            )
        ),
        onDeleteButtonClicked = {},
        onEditButtonClicked = {},
        onAddNewMedicineButtonClicked = {}
    )
}