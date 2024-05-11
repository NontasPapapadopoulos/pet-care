package nondas.pap.petcareapp.presentation.pet

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import nondas.pap.petcareapp.infastracture.navigation.screen.MEDICINE_ROUTE
import nondas.pap.petcareapp.infastracture.navigation.screen.PetScreen
import nondas.pap.petcareapp.presentation.DummyEntities
import nondas.pap.petcareapp.presentation.component.AddHorizontalSpace
import nondas.pap.petcareapp.presentation.component.VerticalSpace
import nondas.pap.petcareapp.presentation.component.EditDeleteButtons
import nondas.pap.petcareapp.presentation.component.GreyBackground
import nondas.pap.petcareapp.presentation.component.LoadingBox
import nondas.pap.petcareapp.presentation.component.MyImage
import nondas.pap.petcareapp.presentation.component.MyText
import nondas.pap.petcareapp.presentation.component.MyTitle
import nondas.pap.petcareapp.presentation.component.PrimaryButton
import nondas.pap.petcareapp.presentation.component.SecondaryButton
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
                onAddNewPetClicked = { navController.navigate(PetScreen.AddPet.route) },
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


    Column(
        modifier = Modifier


    ) {


        Text(
            text ="${content.user.name} pets:",
            style = MaterialTheme.typography.displayMedium
        )


        VerticalSpace(20)


        val petDomainEntity = PetDomainEntity(
            name = "Roza",
            age = 3,
            kind = "dog",
        )

        PetItem(
            petDomainEntity = petDomainEntity,
            onEditButtonClicked = { onEditButtonClicked(petDomainEntity) },
            onDeleteButtonClicked = { showDialog.value = true },
            modifier = Modifier.clickable { onNavigateToPetsMedicineScreen(petDomainEntity) }
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

    val sheetState = rememberModalBottomSheetState()


    if (showDialog.value) {
        ModalBottomSheet(
            onDismissRequest = {},
            sheetState = sheetState
        ) {
            MyTitle(
                title = "Delete ${content.selectedPetDomainEntity!!.name}",
            )

            VerticalSpace(20)

            PrimaryButton(
                onButtonClicked = { onDeleteButtonClicked(content.selectedPetDomainEntity) },
                buttonTitle = "delete",
            )

            VerticalSpace(10)

            SecondaryButton(
                onButtonClicked = { showDialog.value = false },
                buttonTitle = "cancel",
                hasBorder = true,
            )
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
                    text = petDomainEntity.name,
                    fillMaxWidth = false,
                    color = R.color.white
                )

                EditDeleteButtons(
                    onEditButtonClicked = { onEditButtonClicked() },
                    onDeleteButtonClicked = { onDeleteButtonClicked() }
                )
            }


            MyText(
                text = "${petDomainEntity.age.toString()} years old",
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
        content = PetState.Content(
            petDomainEntities = listOf(),
            selectedPetDomainEntity = null,
            user = DummyEntities.user
        ),
        onEditButtonClicked = {},
        onDeleteButtonClicked = {},
        onNavigateToPetsMedicineScreen = {},
        onAddNewPetClicked = {}
    )
}