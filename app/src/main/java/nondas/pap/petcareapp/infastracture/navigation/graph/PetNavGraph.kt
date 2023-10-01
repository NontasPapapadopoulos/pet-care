package nondas.pap.petcareapp.infastracture.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nondas.pap.petcareapp.infastracture.navigation.screen.PETS_ROUTE
import nondas.pap.petcareapp.infastracture.navigation.screen.PetScreen
import nondas.pap.petcareapp.infastracture.navigation.sharedViewModel
import nondas.pap.petcareapp.presentation.home.HomeScreen
import nondas.pap.petcareapp.presentation.pet.AddPetScreen
import nondas.pap.petcareapp.presentation.pet.EditPetScreen
import nondas.pap.petcareapp.presentation.pet.PetViewModel


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

            val viewModel = it.sharedViewModel<PetViewModel>(navController = navController)

            HomeScreen(navController = navController)
        }


        composable(
            route = PetScreen.AddPet.route
        ) {

            val viewModel = it.sharedViewModel<PetViewModel>(navController = navController)


            AddPetScreen(navController = navController)
        }

        composable(
            route = PetScreen.EditPet.route
        ) {
            val viewModel = it.sharedViewModel<PetViewModel>(navController = navController)

            EditPetScreen(navController = navController)
        }

    }




}
