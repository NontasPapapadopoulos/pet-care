package nondas.pap.petcareapp.presentation.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nondas.pap.petcareapp.R


@Composable
fun CircularImage(
    modifier:Modifier = Modifier,
    image: Int,
    size:Int = 40,
    imageRatio:Double = 0.7,
    bgColor:Int = R.color.black
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size.dp)
            .background(
                color = colorResource(id = bgColor),
                shape = CircleShape,
            )
    ) {
        val percentage = (size * imageRatio)
        MyImage(
            imageId = image,
            modifier = modifier
                .width(percentage.dp)
                .height(percentage.dp)
        )

    }
}

@Preview
@Composable
private fun CircularImagePreview() {
    //CircularImage(image = R.drawable.minus)
}