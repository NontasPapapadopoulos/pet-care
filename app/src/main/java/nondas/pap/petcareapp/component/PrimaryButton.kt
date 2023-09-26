package nondas.pap.petcareapp.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.component.MyText


@Composable
fun PrimaryButton(
    buttonTitle: String,
    isEnabled: Boolean = true,
    onButtonClicked: ()->Unit = {},
    textColor:Int = R.color.black,
    fontSize: TextUnit = 16.sp,
    hasBorder: Boolean = true,
    backgroundColor: Int = R.color.white,
    borderColor: Int = R.color.black,
    borderSize: Dp = 1.dp,
    padding: Int = 20,
    modifier: Modifier = Modifier
) {

    Button(
        onClick = { onButtonClicked() },
        border = BorderStroke(
            width = if (hasBorder && isEnabled) borderSize else 0.dp,
            color = if (hasBorder && isEnabled) colorResource(id = borderColor) else colorResource(id = borderColor)
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(padding.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(colorResource(id = backgroundColor)),
        shape = RoundedCornerShape(72.dp),
        enabled = isEnabled,
        elevation = null
    ) {
        MyText(
            text = buttonTitle,
            color = textColor,
            fillMaxWidth = false,
            fontSize = fontSize,
            modifier = Modifier
                .wrapContentSize(),
        )
    }

}

@Preview
@Composable
private fun FlykkPrimaryButtonPreview() {
   // FlykkPrimaryButton("Get Started", Modifier)
}