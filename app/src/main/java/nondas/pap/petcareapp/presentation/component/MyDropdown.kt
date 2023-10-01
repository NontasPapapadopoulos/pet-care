package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nondas.pap.petcareapp.R

@Composable
fun MyDropdown(
    labelTitle: String = "",
    items: List<String> = listOf(),
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    isEnabled: Boolean = true
) {


    var expanded by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        if (labelTitle.isNotEmpty()) {
            MyText(text = labelTitle)
            AddVerticalSpace(6)
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .clickable(
                    onClick = { if (isEnabled) expanded = !expanded })
                .border(
                    1.dp,
                    color = colorResource(id = R.color.grey),
                    shape = RoundedCornerShape(36.dp)
                )
                .background(
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(36.dp)
                )
                .padding(14.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {


            MyText(text = selectedItem)


        }

        if (expanded) {
            ItemsContainer(
                items = items,
                selectedItem = selectedItem,
                onItemSelected = { onItemSelected(it) },
                onExpandedChange = { isExpanded -> expanded = isExpanded }
            )
        }
    }
}



@Composable
fun ItemsContainer(
    items: List<String>,
    onItemSelected: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    selectedItem: String,

) {

    AddVerticalSpace()

    Box(
        Modifier
            .fillMaxWidth()
            .height(330.dp)
            .border(
                1.dp,
                color = colorResource(id = R.color.grey),
                shape = RoundedCornerShape(24.dp)
            )
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(0.dp, 20.dp)
    ) {

        LazyColumn {
            items(items.size) { index ->

                MyText(
                    text = items[index],
                    modifier = Modifier.clickable {
                        onItemSelected(items[index])
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}





@Preview
@Composable
fun DropdownPreview() {




}