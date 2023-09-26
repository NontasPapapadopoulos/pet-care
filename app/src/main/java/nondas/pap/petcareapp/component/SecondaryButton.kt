package com.example.mycomponents.presentation.component

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.component.MyText


@Composable
fun  SecondaryButton(
     buttonTitle: String,
     onButtonClicked: ()->Unit = {},
     textColor: Int = R.color.black,
     hasBorder: Boolean = true,
     isTransparent: Boolean = false,
     backgroundColor: Int = R.color.white,
     borderColor: Int = R.color.black,
     padding: Int = 20,
     modifier: Modifier = Modifier
) {
    Button(
        onClick = { onButtonClicked() },
        border = BorderStroke(width = if (hasBorder) 1.dp else 0.dp,
            color = if (hasBorder) colorResource(id = borderColor) else colorResource(id = borderColor)),
        shape = RoundedCornerShape(72.dp),
        colors = if (isTransparent) ButtonDefaults.buttonColors(Color.Transparent) else ButtonDefaults.buttonColors(colorResource(id = backgroundColor)),
        elevation = null,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(padding.dp, 0.dp)
        ) {

        MyText(
            text = buttonTitle,
            color = textColor,
            fillMaxWidth = false,
            fontSize = 16.sp,
            modifier = Modifier
                .wrapContentSize(),
        )
}

}

@Preview
@Composable
private fun FlykkSecondaryButtonPreview() {
    SecondaryButton(
        "Get Started",
        textColor = R.color.white,
        onButtonClicked = {},
        hasBorder = false,
        isTransparent = true,
        modifier = Modifier)
}