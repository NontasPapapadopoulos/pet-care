package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
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
fun MyTitle(title: String,
            fontSize: TextUnit = 24.sp,
            textColor: Int = R.color.black,
            textAlignment: TextAlign = TextAlign.Center,
            modifier: Modifier = Modifier) {
    MyText(
        text = title,
        fontSize = fontSize,
        color = textColor,
        fontWeight = FontWeight.W700,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        )

}



@Preview
@Composable
private fun FlykkPrimaryTitlePreview() {
    MyTitle(title = "Test title!!", fontSize = 17.sp, textColor = R.color.black, modifier = Modifier)
}