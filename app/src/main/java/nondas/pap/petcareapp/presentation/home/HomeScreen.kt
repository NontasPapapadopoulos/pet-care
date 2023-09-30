package nondas.pap.petcareapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.domain.model.Pet
import nondas.pap.petcareapp.presentation.component.AddHorizontalSpace
import nondas.pap.petcareapp.presentation.component.AddVerticalSpace
import nondas.pap.petcareapp.presentation.component.MyImage
import nondas.pap.petcareapp.presentation.component.MyText


@Composable
fun HomeScreen(
    navController: NavController
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.mpez))
    ) {



        PetItem(
            pet = Pet(
                name = "Roza",
                age = 3,
                breed = "Frenchie",
                kind = "dog",
            )
        )




    }
}


@Composable
fun PetItem(
    pet: Pet
) {

    Row(
        modifier = Modifier
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
            MyText(
                text = pet.name,
                fillMaxWidth = false,
                color = R.color.white
            )
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
    HomeScreen(navController = rememberNavController())
}