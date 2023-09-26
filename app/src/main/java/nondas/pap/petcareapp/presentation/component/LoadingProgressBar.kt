package nondas.pap.petcareapp.presentation.component

import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import nondas.pap.petcareapp.R


@Composable
fun LoadingProgressBar(
    isLoading: Boolean,
    changeBackgroundColor: Boolean = true,
    screenBarToBeCovered: ScreenBar = ScreenBar.STATUS_AND_NAVIGATION_BAR) {

    val loadingColor = colorResource(id = R.color.black)
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

//
//
//    currentActivity.getWindow().setFlags(
//        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
//    )
//TODO: disable the window when it is in loading state

    // this is for Screens with white background such as Transaction list screen
    if (screenBarToBeCovered == ScreenBar.STATUS_AND_NAVIGATION_BAR) {
        systemUiController.setSystemBarsColor(
            color = if (isLoading) loadingColor else Color.Transparent,
            darkIcons = useDarkIcons
        )
    }

    // this is for Screens with purple background such as login screen
    if (screenBarToBeCovered == ScreenBar.NAVIGATION_BAR) {
        systemUiController.setNavigationBarColor(
            color = if (isLoading) loadingColor else Color.Transparent,
            darkIcons = useDarkIcons
        )
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = if (changeBackgroundColor) loadingColor else Color.Transparent)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) {}
        ) {

            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center),
                color = colorResource(id = R.color.purple_700)
            )

        }
    }
}

 enum class ScreenBar {
     NAVIGATION_BAR,
     STATUS_BAR,
     STATUS_AND_NAVIGATION_BAR,
     NONE

 }


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingProgressBarPreview() {
    LoadingProgressBar(isLoading = true)
}