package nondas.pap.petcareapp.component.inputText

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
import nondas.pap.petcareapp.component.MyImage


@Composable
fun PairImagesEnd(
    imageEndId: Int,
    imageEndWidth: Int,
    imageEndHeight: Int,
    spaceBetween: Int = 10,
    endImageHasVerticalLine: Boolean,
    modifier: Modifier = Modifier
) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        if (endImageHasVerticalLine) {

            Spacer(modifier = Modifier.width(5.dp))


            MyImage(
                imageId = R.drawable.line,
                modifier = Modifier
                    .width(1.dp)
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.width(spaceBetween.dp))
        }
        else
            Spacer(modifier = Modifier.width(10.dp))



        MyImage(
            imageId = imageEndId,
            modifier = Modifier
                .height(imageEndHeight.dp)
                .width(imageEndWidth.dp)
        )

    }
}

@Preview
@Composable
private fun PairImagesEndPreview() {

    PairImagesEnd(
        imageEndId = R.drawable.ic_launcher_background,
        imageEndWidth = 20,
        imageEndHeight = 20,
        spaceBetween = 13,
        endImageHasVerticalLine = true
    )
}