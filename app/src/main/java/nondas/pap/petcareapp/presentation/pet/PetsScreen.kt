package nondas.pap.petcareapp.presentation.pet

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import nondas.pap.petcareapp.presentation.navigation.Screen
import nondas.pap.petcareapp.presentation.navigation.screen.MEDICINE_ROUTE
import nondas.pap.petcareapp.presentation.DummyEntities
import nondas.pap.petcareapp.presentation.component.AddHorizontalSpace
import nondas.pap.petcareapp.presentation.component.VerticalSpace
import nondas.pap.petcareapp.presentation.component.EditDeleteButtons
import nondas.pap.petcareapp.presentation.component.LoadingBox
import nondas.pap.petcareapp.presentation.user


@Composable
fun PetsScreen(
    viewModel: PetViewModel = hiltViewModel(),
    navController: NavController
) {

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.errorFlow.collect { error ->
            Toast.makeText(
                context,
                error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(val state = uiState) {
        is PetState.Content -> {
            PetsContent(
                content = state,
                onEditButtonClicked = { viewModel.add(PetEvent.EditButtonClicked(it)) },
                onDeleteButtonClicked = { viewModel.add(PetEvent.DeleteButtonClicked(it)) },
                onAddNewPetClicked = { navController.navigate(Screen.AddPet.name) },
                onNavigateToPetsMedicineScreen = {
                    // TODO: pass the pet to the arguements
                    navController.navigate(route = MEDICINE_ROUTE) },
            )
        }
        PetState.Idle -> {
            LoadingBox()
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PetsContent(
    content: PetState.Content,
    onEditButtonClicked: (PetDomainEntity) -> Unit,
    onDeleteButtonClicked: (PetDomainEntity) -> Unit,
    onNavigateToPetsMedicineScreen: (PetDomainEntity) -> Unit,
    onAddNewPetClicked: () -> Unit,

) {
    val showDialog = remember { mutableStateOf(false) }

    Scaffold {

        Column(
            modifier = Modifier.padding(it)
        ) {


        Text(
            text ="${content.user.name} pets:",
            style = MaterialTheme.typography.displayMedium
        )


            VerticalSpace(20)


            content.pets.forEach { pet ->
                PetItem(
                    petDomainEntity = pet,
                    onEditButtonClicked = { onEditButtonClicked(pet) },
                    onDeleteButtonClicked = { showDialog.value = true },
                    modifier = Modifier.clickable { onNavigateToPetsMedicineScreen(pet) }
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
                        .clickable { onAddNewPetClicked() }
                )
            }
        }


        val sheetState = rememberModalBottomSheetState()


        if (showDialog.value) {
            ModalBottomSheet(
                onDismissRequest = {},
                sheetState = sheetState
            ) {


                Text(text = "Delete ${content.selectedPetDomainEntity!!.name}")

                VerticalSpace(20)

                Button(
                    onClick = { onDeleteButtonClicked(content.selectedPetDomainEntity) },
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



@Composable
fun PetItem(
    petDomainEntity: PetDomainEntity,
    onEditButtonClicked: () -> Unit,
    onDeleteButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp)
            .background(
                color = colorResource(id = R.color.pink),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {


        Icon(
            Icons.Default.Pets,
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )

        AddHorizontalSpace(20)

        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = petDomainEntity.name,
                )

                EditDeleteButtons(
                    onEditButtonClicked = { onEditButtonClicked() },
                    onDeleteButtonClicked = { onDeleteButtonClicked() }
                )
            }


            Text(
                text = "${petDomainEntity.age} years old",
            )
        }
    }
}


@Composable
@Preview
private fun HomeScreenPreview() {
    PetsContent(
        content = PetState.Content(
            pets = listOf(),
            selectedPetDomainEntity = null,
            user = DummyEntities.user
        ),
        onEditButtonClicked = {},
        onDeleteButtonClicked = {},
        onNavigateToPetsMedicineScreen = {},
        onAddNewPetClicked = {}
    )
}