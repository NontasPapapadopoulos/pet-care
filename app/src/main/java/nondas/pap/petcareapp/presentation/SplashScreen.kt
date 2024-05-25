package nondas.pap.petcareapp.presentation

import android.app.Activity
import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
import androidx.navigation.NavController
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.ui.theme.PetCareAppTheme

@Composable
fun SplashScreen() {

    //val view = LocalView.current

  //  val window = (view.context as Activity).window
//    val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
//
//    windowInsetsController!!.hide(WindowInsetsCompat.Type.navigationBars())
//    windowInsetsController.systemBarsBehavior = BEHAVIOR_DEFAULT

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            Icons.Default.Pets,
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Pet Care",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.weight(1f))


    }
}


@Preview
@Composable
private fun SplashScreenPreview() {
    PetCareAppTheme {
        SplashScreen()
    }
}