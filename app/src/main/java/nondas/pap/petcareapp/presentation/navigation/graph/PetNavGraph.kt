package nondas.pap.petcareapp.presentation.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import nondas.pap.petcareapp.presentation.navigation.EditPetRoute
import nondas.pap.petcareapp.presentation.navigation.NavArg
import nondas.pap.petcareapp.presentation.navigation.Route
import nondas.pap.petcareapp.presentation.navigation.Screen
import nondas.pap.petcareapp.presentation.navigation.params

import nondas.pap.petcareapp.presentation.pet.EditPetScreen
import nondas.pap.petcareapp.presentation.pet.PetsScreen
import nondas.pap.petcareapp.presentation.pet.add.AddPetScreen


fun NavGraphBuilder.petsNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.Main.name ,
        route = Route.Pet.name
    ) {


        composable(
            route = Screen.Main.name
        ) {
            PetsScreen(
                onNavigateToAddPetScreen = { navController.navigate(Screen.AddPet.name)},
                onNavigateToEditPetScreen = { navController.navigate(Screen.EditPet.params(NavArg.petId)) },
                onNavigateToPetMedicineScreen = { navController.navigate(Screen.Medicine.params(NavArg.petId)) }
            )
        }


        composable(
            route = Screen.AddPet.name
        ) {

            AddPetScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = EditPetRoute,
            arguments = listOf(navArgument(NavArg.petId.param) {
                type = NavType.StringType
            })
        ) {

            EditPetScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

    }

}
