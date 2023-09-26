package nondas.pap.petcareapp.component

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun ManageSystemBars() {
    val view = LocalView.current
    val window = (view.context as Activity).window
    val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)

//    val isKeyboardOpen by keyboardAsState()
//
//    if (isKeyboardOpen == Keyboard.Opened)
//        showNavigationBar(windowInsetsController)
//    else
//        hideNavigationBar(windowInsetsController)
    windowInsetsController!!.show(WindowInsetsCompat.Type.navigationBars())


  //  windowInsetsController!!.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT


//    DisposableEffect(key1 = true) {
//
//        onDispose {
//            windowInsetsController.show(WindowInsetsCompat.Type.navigationBars())
//            windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
//        }
//    }

}

@Composable
private fun hideNavigationBar(windowInsetsController: WindowInsetsControllerCompat?) {
    windowInsetsController!!.hide(WindowInsetsCompat.Type.navigationBars())
}

@Composable
private fun showNavigationBar(windowInsetsController: WindowInsetsControllerCompat?) {
    windowInsetsController!!.show(WindowInsetsCompat.Type.navigationBars())
}