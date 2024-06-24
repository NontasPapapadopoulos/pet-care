package nondas.pap.petcareapp.presentation.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nondas.pap.petcareapp.presentation.navigation.Route
import nondas.pap.petcareapp.presentation.navigation.Screen

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
                navController = navController,
            )
        }


        composable(
            route = Screen.AddPet.name
        ) {

            AddPetScreen(
                navController = navController,
            )
        }

        composable(
            route = Screen.EditPet.name
        ) {

            EditPetScreen(
                navController = navController
            )
        }

    }

}
