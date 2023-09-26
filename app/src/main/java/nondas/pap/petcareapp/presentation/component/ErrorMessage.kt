package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nondas.pap.petcareapp.R


@Composable
fun ErrorMessage(errorMessage: String) {
    MyText(
        text = errorMessage,
        fontSize = 12.sp,
        color = R.color.black,
        fillMaxWidth = false,
        modifier = Modifier
            .padding(20.dp, 0.dp)

    )
}