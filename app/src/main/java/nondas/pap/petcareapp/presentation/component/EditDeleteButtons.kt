package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditDeleteButtons(
    onEditButtonClicked: () -> Unit,
    onDeleteButtonClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        Icon(
            Icons.Default.Edit,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clickable { onEditButtonClicked() }
        )

        AddHorizontalSpace()

        Icon(
            Icons.Default.Delete,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clickable { onDeleteButtonClicked() }
        )
    }
}

