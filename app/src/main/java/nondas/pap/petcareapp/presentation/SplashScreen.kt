package nondas.pap.petcareapp.presentation

import android.app.Activity
import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
import androidx.navigation.NavController
import nondas.pap.petcareapp.R

@Composable
fun SplashScreen(
    navController: NavController
) {

    val view = LocalView.current

    val window = (view.context as Activity).window
    val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)

    windowInsetsController!!.hide(WindowInsetsCompat.Type.navigationBars())
    windowInsetsController.systemBarsBehavior = BEHAVIOR_DEFAULT

    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.red))
    ) {



    }
}