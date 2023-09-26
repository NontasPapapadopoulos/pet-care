package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddHorizontalSpace(dp: Int = 10) {
    Spacer(modifier = Modifier.width(dp.dp))
}