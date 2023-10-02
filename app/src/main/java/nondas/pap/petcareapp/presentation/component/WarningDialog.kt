package nondas.pap.petcareapp.presentation.component

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import nondas.pap.petcareapp.R

@Composable
fun WarningDialog(
    title: String,
    primaryButtonText: String,
    secondaryButtonText: String,
    onDismiss: () -> Unit = {},
    onPrimaryButtonClicked: () -> Unit = {},
    onSecondaryButtonClicked: () -> Unit = {},
    modifier: Modifier
) {


    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(color = colorResource(id = R.color.white))
            .padding(bottom = 20.dp, top = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DialogGreyLine()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            horizontalArrangement = Arrangement.End
        ) {
            XButton(
                onButtonClicked = { onDismiss() },
            )
        }

        AddVerticalSpace(20)

        MyTitle(
            title = title,
        )

        AddVerticalSpace(20)

        PrimaryButton(
            onButtonClicked = { onPrimaryButtonClicked() },
            buttonTitle = primaryButtonText,
        )

        AddVerticalSpace(10)

        SecondaryButton(
            onButtonClicked = { onSecondaryButtonClicked() },
            buttonTitle = secondaryButtonText,
            hasBorder = true,
        )

    }


}
