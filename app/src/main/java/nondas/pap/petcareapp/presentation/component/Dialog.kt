package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nondas.pap.petcareapp.R


@Composable
fun Dialog(
    title: String,
    primaryButtonTitle: String,
    secondaryButtonTitle: String,
    onPrimaryButtonClicked: ()-> Unit = {},
    onSecondaryButtonClicked: ()-> Unit = {},
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp, 0.dp)
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(size = 24.dp)
            )
            .padding(24.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            XButton(
                onButtonClicked = { /*TODO*/ },
            )
        }

        MyText(
            text = title,
            fontSize = 20.sp,
        )

        VerticalSpace(40)

        PrimaryButton(
            buttonTitle = primaryButtonTitle,
            onButtonClicked = { onPrimaryButtonClicked() },
            padding = 0
        )

        VerticalSpace(12)

        SecondaryButton(
            buttonTitle = secondaryButtonTitle,
            onButtonClicked = { onSecondaryButtonClicked() },
            padding = 0
        )
    }


}


@Preview
@Composable
private fun DialogPreview() {
    Dialog(
        title = "xxxx",
        primaryButtonTitle = "xxx",
        secondaryButtonTitle = "xxxx",
        onPrimaryButtonClicked = { },
        onSecondaryButtonClicked = {}
    )
}