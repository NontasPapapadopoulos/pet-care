package nondas.pap.petcareapp.presentation.medicine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.presentation.component.AddVerticalSpace
import nondas.pap.petcareapp.presentation.component.MyTitle


@Composable
fun MedicineScreen(
    navController: NavController
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mpez))
    ) {

        AddVerticalSpace(50)

        MyTitle(
            title = "Pet name medicine",
            textColor = R.color.dark_red
        )




    }
}







@Preview
@Composable
private fun MedicineScreenPreview() {
    MedicineScreen(navController = rememberNavController())
}