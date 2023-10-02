package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import nondas.pap.petcareapp.R


@Composable
fun DialogGreyLine() {
    Row(
        modifier = Modifier
            .width(70.dp)
            .height(5.dp)
            .background(
                color = colorResource(id = R.color.grey),
                shape = RoundedCornerShape(size = 12.dp)
            )
    ) {}
}