package nondas.pap.petcareapp.presentation.component.inputText

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.presentation.component.MyImage


@Composable
fun PairImagesStart(
    startImageId: Int,
    startImageWidth: Int,
    startImageHeight: Int,
    startImageHasVerticalLine: Boolean
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {


        MyImage(
            imageId = startImageId,
            modifier = Modifier
                .width(startImageWidth.dp)
                .height(startImageHeight.dp)
        )

        if (startImageHasVerticalLine) {

            Spacer(modifier = Modifier.width(8.dp))

            MyImage(
                imageId = R.drawable.line,
                modifier = Modifier
                    .width(1.dp)
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))
        }

        else
            Spacer(modifier = Modifier.width(10.dp))
    }
}

@Preview
@Composable
private fun PairImagesStartPreview() {
    PairImagesStart(
        startImageId = R.drawable.ic_launcher_background,
        startImageWidth = 20,
        startImageHeight = 20,
        startImageHasVerticalLine = true
    )
}