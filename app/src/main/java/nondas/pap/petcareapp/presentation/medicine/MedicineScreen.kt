package nondas.pap.petcareapp.presentation.medicine

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import nondas.pap.petcareapp.domain.model.Medicine
import nondas.pap.petcareapp.domain.model.TimePeriod
import nondas.pap.petcareapp.infastracture.navigation.screen.Screen
import nondas.pap.petcareapp.presentation.component.AddHorizontalSpace
import nondas.pap.petcareapp.presentation.component.AddVerticalSpace
import nondas.pap.petcareapp.presentation.component.EditDeleteButtons
import nondas.pap.petcareapp.presentation.component.MyImage
import nondas.pap.petcareapp.presentation.component.MyText
import nondas.pap.petcareapp.presentation.component.MyTitle
import java.util.Date


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

        AddVerticalSpace(30)


        MedicineItem(
            medicine = Medicine(
                type = "Vaccine",
                dateReceived = Date(),
                repeatRate = TimePeriod.EVERY_YEAR,
                comments = "xxx"
            )
        )


        Spacer(modifier = Modifier.weight(1f))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            MyImage(
                imageId = R.drawable.baseline_add_circle_24_blue,
                modifier = Modifier
                    .size(60.dp)
                    .clickable { navController.navigate(route = Screen.AddMedicine.route) }
            )
        }

    }
}




@Composable
fun MedicineItem(medicine: Medicine) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp)
            .background(
                color = colorResource(id = R.color.light_blue),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        MyImage(
            imageId = R.drawable.ic_launcher_foreground,
            modifier = Modifier.size(40.dp)
        )

        AddHorizontalSpace(15)

        Column {

            Row(
                modifier = Modifier
                            .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                MyText(
                    text = medicine.type,
                    fillMaxWidth = false,
                    color = R.color.white
                )

                EditDeleteButtons()
            }

            MyText(
                text = medicine.dateReceived.toString(),
                fillMaxWidth = false,
                color = R.color.white
            )
            MyText(
                text = medicine.repeatRate.frequency,
                fillMaxWidth = false,
                color = R.color.white
            )
            MyText(
                text = medicine.comments,
                fillMaxWidth = false,
                color = R.color.white
            )



        }

    }
}


@Preview
@Composable
private fun MedicineScreenPreview() {
    MedicineScreen(navController = rememberNavController())
}