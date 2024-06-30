package nondas.pap.petcareapp.presentation.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import nondas.pap.petcareapp.presentation.navigation.screen.MEDICINE_ROUTE
import nondas.pap.petcareapp.presentation.navigation.screen.MedicineScreen
import nondas.pap.petcareapp.presentation.medicine.PetMedicineScreen
import nondas.pap.petcareapp.presentation.medicine.add.AddMedicineScreen
import nondas.pap.petcareapp.presentation.medicine.edit.EditMedicineScreen
import nondas.pap.petcareapp.presentation.navigation.AddMedicineRoute
import nondas.pap.petcareapp.presentation.navigation.MedicineRoute
import nondas.pap.petcareapp.presentation.navigation.NavArg
import nondas.pap.petcareapp.presentation.navigation.Screen
import nondas.pap.petcareapp.presentation.navigation.params


fun NavGraphBuilder.medicineNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = MedicineRoute ,
        route = MEDICINE_ROUTE
    ) {


        composable(
            route = MedicineRoute,
            arguments = listOf(navArgument(NavArg.petId.param) {
                type = NavType.StringType
            })
        ) {
            PetMedicineScreen(
                onNavigateToAddNewMedicineScreen = { navController.navigate(Screen.AddMedicine.params(NavArg.petId)) }
            )
        }

        composable(
            route = AddMedicineRoute,
            arguments = listOf(navArgument(NavArg.petId.param) {
                type = NavType.StringType
            })
        ) {

            AddMedicineScreen(
                onNavigateBack = { navController.popBackStack() },
            )
        }


        composable(
            route = MedicineScreen.EditMedicine.route
        ) {


            EditMedicineScreen(
                onNavigateBack = { navController.popBackStack() },
            )
        }


    }

}
