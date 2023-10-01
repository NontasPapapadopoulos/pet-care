package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nondas.pap.petcareapp.R

@Composable
fun EditDeleteButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        MyImage(
            imageId = R.drawable.baseline_edit_24,
            modifier = Modifier
                .size(24.dp)
                .clickable{ }
        )

        AddHorizontalSpace()

        MyImage(
            imageId = R.drawable.baseline_delete_24,
            modifier = Modifier
                .size(24.dp)
                .clickable{}
        )
    }
}

