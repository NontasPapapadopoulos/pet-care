package nondas.pap.petcareapp.presentation.pet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.domain.model.Pet
import nondas.pap.petcareapp.infastracture.navigation.screen.MEDICINE_ROUTE
import nondas.pap.petcareapp.infastracture.navigation.screen.PetScreen
import nondas.pap.petcareapp.presentation.component.AddHorizontalSpace
import nondas.pap.petcareapp.presentation.component.AddVerticalSpace
import nondas.pap.petcareapp.presentation.component.EditDeleteButtons
import nondas.pap.petcareapp.presentation.component.GreyBackground
import nondas.pap.petcareapp.presentation.component.MyImage
import nondas.pap.petcareapp.presentation.component.MyText
import nondas.pap.petcareapp.presentation.component.MyTitle
import nondas.pap.petcareapp.presentation.component.WarningDialog
import nondas.pap.petcareapp.presentation.pet.PetEvent
import nondas.pap.petcareapp.presentation.pet.PetState


@Composable
fun PetsScreen(
    viewModel: PetViewModel = hiltViewModel(),
    navController: NavController
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    PetsContent(
        selectedPet = state.selectedPet,
        onEditButtonClicked = { viewModel.add(PetEvent.EditButtonClicked(it)) },
        onDeleteButtonClicked = { viewModel.add(PetEvent.DeleteButtonClicked(it)) },
        onAddNewPetClicked = { navController.navigate(PetScreen.AddPet.route) },
        onNavigateToPetsMedicineScreen = {
            // TODO: pass the pet to the arguements
            navController.navigate(route = MEDICINE_ROUTE) },
    )
}

@Composable
private fun PetsContent(
    selectedPet: Pet,
    onEditButtonClicked: (Pet) -> Unit,
    onDeleteButtonClicked: (Pet) -> Unit,
    onNavigateToPetsMedicineScreen: (Pet) -> Unit,
    onAddNewPetClicked: () -> Unit,

) {
    val showDialog = remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .background(colorResource(id = R.color.mpez)),
    ) {

        val (column, dialog) = createRefs()


        Column(
            modifier = Modifier
                .constrainAs(column) {
                    top.linkTo(parent.top)
                }

        ) {

            AddVerticalSpace(50)

            Text(
                text ="Yourname pets:",
                style = MaterialTheme.typography.displayMedium
            )


            AddVerticalSpace(20)


            val pet = Pet(
                name = "Roza",
                age = 3,
                breed = "Frenchie",
                kind = "dog",
            )

            PetItem(
                pet = pet,
                onEditButtonClicked = { onEditButtonClicked(pet) },
                onDeleteButtonClicked = { showDialog.value = true },
                modifier = Modifier.clickable { onNavigateToPetsMedicineScreen(pet) }
            )

            Spacer(modifier = Modifier.weight(1f))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                MyImage(
                    imageId =
                    R.drawable.baseline_add_circle_24,
                    modifier = Modifier
                        .size(60.dp)
                        .clickable { onAddNewPetClicked() }
                )
            }


        }

        GreyBackground(isVisible = showDialog.value)

        if (showDialog.value) {


            WarningDialog(
                title = "Delete ${selectedPet.name}",
                primaryButtonText = "delete",
                secondaryButtonText = "cancel",
                onPrimaryButtonClicked = { onDeleteButtonClicked(selectedPet) },
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
fun PetItem(
    pet: Pet,
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

        MyImage(
            imageId = R.drawable.ic_launcher_foreground,
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
                MyText(
                    text = pet.name,
                    fillMaxWidth = false,
                    color = R.color.white
                )

                EditDeleteButtons(
                    onEditButtonClicked = { onEditButtonClicked() },
                    onDeleteButtonClicked = { onDeleteButtonClicked() }
                )
            }

            MyText(
                text = pet.breed,
                fillMaxWidth = false,
                color = R.color.white

            )
            MyText(
                text = "${pet.age.toString()} years old",
                fillMaxWidth = false,
                color = R.color.white
            )
        }
    }
}


@Composable
@Preview
private fun HomeScreenPreview() {
    PetsContent(
        selectedPet = Pet(),
        onEditButtonClicked = {},
        onDeleteButtonClicked = {},
        onNavigateToPetsMedicineScreen = {},
        onAddNewPetClicked = {}
    )
}