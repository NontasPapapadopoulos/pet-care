package nondas.pap.petcareapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nondas.pap.petcareapp.R

@Composable
fun BackButton(
    onButtonClicked: ()->Unit,
    modifier: Modifier = Modifier,
    color: Int = R.color.black,
    layout: Layout = Layout.CONSTRAINT_LAYOUT
    ) {

    if (layout == Layout.CONSTRAINT_LAYOUT) {

        Box(
            modifier = modifier
        ) {
            IconButton(
                onClick = { onButtonClicked() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back_button),
                    contentDescription = null,
                    tint = colorResource(id = color),
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }
    }

    else {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            horizontalArrangement = Arrangement.Start
) {
            Box(
                modifier = modifier
            ) {
                IconButton(
                    onClick = { onButtonClicked() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_button),
                        contentDescription = null,
                        tint = colorResource(id = color),
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }
        }
    }
}





enum class Layout {
    CONSTRAINT_LAYOUT,
    COLUMN
}

@Preview
@Composable
fun BackButtonPreview() {
    BackButton({},Modifier)

}