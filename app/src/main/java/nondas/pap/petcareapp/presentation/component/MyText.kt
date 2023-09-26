package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import nondas.pap.petcareapp.R


@Composable
fun MyText(
    text: String,
    fontSize: TextUnit = 16.sp,
    fillMaxWidth: Boolean = true,
    fontWeight: FontWeight = FontWeight.W400,
    color: Int = R.color.black,
    textAlignment: TextAlign = TextAlign.Center,
    maxLines:Int = Int.MAX_VALUE,
    modifier: Modifier = Modifier
) {

    val flykkTextModifier = modifier.background(color = Color.Transparent)


    Text(
        text = text,
        fontSize = fontSize,
        textAlign = textAlignment,
        color = colorResource(id = color),
        lineHeight = 24.sp,
        maxLines = maxLines,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = fontWeight,
        modifier = if (fillMaxWidth)
            flykkTextModifier.fillMaxWidth()
         else
            flykkTextModifier
        )

}



@Preview
@Composable
private fun FlykkSecondaryTextPreview() {
    MyText(
        text = "Opening an account with flykk is quick\n" +
            "and easy, first we need to know how to contact you.",
        fontSize = 16.sp,
        color = R.color.black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier)
}