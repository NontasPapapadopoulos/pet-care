package nondas.pap.petcareapp.component


import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import nondas.pap.petcareapp.R


@Composable
fun MySwitch(
    modifier: Modifier,
    isChecked: Boolean = false,
    onSwitch: () -> Unit = {}
) {


    Switch(
        checked = isChecked,
        onCheckedChange = { onSwitch() },
        colors = SwitchDefaults.colors(
            checkedThumbColor = colorResource(id = R.color.black),
            uncheckedThumbColor = colorResource(id = R.color.purple_700)
        )
    )
}


@Preview
@Composable
private fun FlykkSwitchPreview() {
    MySwitch(
        modifier = Modifier,
        isChecked = true,
        onSwitch = {}
    )
}