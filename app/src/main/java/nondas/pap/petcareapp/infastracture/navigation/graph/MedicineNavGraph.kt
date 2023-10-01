package nondas.pap.petcareapp.infastracture.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nondas.pap.petcareapp.infastracture.navigation.screen.MEDICINE_ROUTE
import nondas.pap.petcareapp.infastracture.navigation.screen.MedicineScreen
import nondas.pap.petcareapp.presentation.medicine.AddMedicineScreen
import nondas.pap.petcareapp.presentation.medicine.EditMedicineScreen
import nondas.pap.petcareapp.presentation.medicine.MedicineScreen
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


            MedicineScreen(navController = navController)
        }

        composable(
            route = MedicineScreen.AddMedicine.route
        ) {

            val viewModel = it.sharedViewModel<MedicineViewModel>(navController = navController)

            AddMedicineScreen(navController = navController)
        }


        composable(
            route = MedicineScreen.EditMedicine.route
        ) {

            val viewModel = it.sharedViewModel<MedicineViewModel>(navController = navController)

            EditMedicineScreen(navController = navController)
        }


    }

}
