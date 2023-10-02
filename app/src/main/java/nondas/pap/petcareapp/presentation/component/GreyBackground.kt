package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import nondas.pap.petcareapp.R

@Composable
fun GreyBackground(isVisible: Boolean) {

    val transparentColor = colorResource(id = R.color.transparent)
    val useDarkIcons = !isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(
        color = if (isVisible) transparentColor else Color.Transparent,
        darkIcons = useDarkIcons
    )

    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = transparentColor)
        )
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GreyBackgroundPreview() {
    GreyBackground(isVisible = true)
}