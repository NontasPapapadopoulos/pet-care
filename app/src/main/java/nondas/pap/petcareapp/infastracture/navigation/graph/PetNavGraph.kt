package nondas.pap.petcareapp.infastracture.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nondas.pap.petcareapp.infastracture.navigation.screen.PETS_ROUTE
import nondas.pap.petcareapp.infastracture.navigation.screen.PetScreen
import nondas.pap.petcareapp.presentation.pet.AddPetScreen
import nondas.pap.petcareapp.presentation.pet.EditPetScreen
import nondas.pap.petcareapp.presentation.pet.PetViewModel
import nondas.pap.petcareapp.presentation.pet.PetsScreen


fun NavGraphBuilder.petsNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = PetScreen.Pets.route ,
        route = PETS_ROUTE
    ) {


        composable(
            route = PetScreen.Pets.route
        ) {


            PetsScreen(
                navController = navController,
            )
        }


        composable(
            route = PetScreen.AddPet.route
        ) {

            AddPetScreen(
                navController = navController,
            )
        }

        composable(
            route = PetScreen.EditPet.route
        ) {

            EditPetScreen(
                navController = navController
            )
        }

    }




}
