package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nondas.pap.petcareapp.R


@Composable
fun FieldLabel(labelTitle: String, color: Int = R.color.black ) {
    MyText(
        text = labelTitle,
        fontSize = 14.sp,
        color = color,
        modifier = Modifier
            .height(20.dp)
            .background(color = Color.Transparent),
        textAlignment = TextAlign.Start
    )
}

@Preview
@Composable
private fun FieldLabelPreview() {
    FieldLabel("First Name")
}