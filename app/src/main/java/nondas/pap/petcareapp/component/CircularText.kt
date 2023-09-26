package nondas.pap.petcareapp.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nondas.pap.petcareapp.R


@Composable
fun CircularText(
    text: String,
    textColor:Int = R.color.black,
    modifier: Modifier = Modifier) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(40.dp)
            .background(
                color = colorResource(id = R.color.white),
            shape = CircleShape,
            )
    ) {


        MyText(
            text = text,
            fontSize = 14.sp,
            color = textColor,
            fillMaxWidth = false
        )



    }
}

@Preview
@Composable
private fun CircularTextPreview() {
}



