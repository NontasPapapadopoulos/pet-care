package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun VerticalSpace(dp: Int = 10) {
    Spacer(modifier = Modifier.height(dp.dp))
}