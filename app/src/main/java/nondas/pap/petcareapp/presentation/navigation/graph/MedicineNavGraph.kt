package nondas.pap.petcareapp.presentation.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nondas.pap.petcareapp.presentation.navigation.screen.MEDICINE_ROUTE
import nondas.pap.petcareapp.presentation.navigation.screen.MedicineScreen
import nondas.pap.petcareapp.presentation.medicine.PetMedicineScreen
import nondas.pap.petcareapp.presentation.medicine.add.AddMedicineScreen
import nondas.pap.petcareapp.presentation.medicine.edit.EditMedicineScreen


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



            PetMedicineScreen(
                navController = navController,
            )
        }

        composable(
            route = MedicineScreen.AddMedicine.route
        ) {


            AddMedicineScreen(
                navController = navController,
            )
        }


        composable(
            route = MedicineScreen.EditMedicine.route
        ) {


            EditMedicineScreen(
                navController = navController,
            )
        }


    }

}
