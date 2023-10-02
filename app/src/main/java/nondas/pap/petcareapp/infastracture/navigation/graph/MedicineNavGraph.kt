package nondas.pap.petcareapp.infastracture.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nondas.pap.petcareapp.infastracture.navigation.screen.MEDICINE_ROUTE
import nondas.pap.petcareapp.infastracture.navigation.screen.MedicineScreen
import nondas.pap.petcareapp.presentation.medicine.AddMedicineScreen
import nondas.pap.petcareapp.presentation.medicine.EditMedicineScreen
import nondas.pap.petcareapp.presentation.medicine.PetMedicineScreen
import nondas.pap.petcareapp.presentation.medicine.MedicineViewModel


fun NavGraphBuilder.medicineNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = MedicineScreen.Medicine.route ,
        route = MEDICINE_ROUTE
    ) {


        composable(
            route = MedicineScreen.Medicine.route
        ) {

            val viewModel = it.sharedViewModel<MedicineViewModel>(navController = navController)


            PetMedicineScreen(
                navController = navController,
                state = viewModel.state,
                onEvent = viewModel::onEvent
                )
        }

        composable(
            route = MedicineScreen.AddMedicine.route
        ) {

            val viewModel = it.sharedViewModel<MedicineViewModel>(navController = navController)

            AddMedicineScreen(
                navController = navController,
                state = viewModel.state,
                onEvent = viewModel::onEvent
            )
        }


        composable(
            route = MedicineScreen.EditMedicine.route
        ) {

            val viewModel = it.sharedViewModel<MedicineViewModel>(navController = navController)

            EditMedicineScreen(
                navController = navController,
                state = viewModel.state,
                onEvent = viewModel::onEvent
            )
        }


    }

}
