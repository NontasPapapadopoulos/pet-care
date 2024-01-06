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
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nondas.pap.petcareapp.R

@Composable
private fun MyDropdown(
    labelTitle: String = "",
    items: List<String> = listOf(),
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier
) {


    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        if (labelTitle.isNotEmpty()) {
            MyText(
                text = labelTitle,
                textAlignment = TextAlign.Start,
                color = R.color.grey
            )
            AddVerticalSpace(6)
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .clickable(
                    onClick = { expanded = !expanded })
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


            MyText(
                text = selectedItem,
                color = R.color.grey
            )


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
            .wrapContentHeight()
            .border(
                1.dp,
                color = colorResource(id = R.color.grey),
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(0.dp, 20.dp)
    ) {

//        LazyColumn {
//            items(items.size) { index ->
        Column {
            items.forEach { item ->
                MyText(
                    text = item,
                    color = R.color.grey,
                    modifier = Modifier
                        .clickable {
                            onItemSelected(item)
                            onExpandedChange(false)
                        }
                )

                AddVerticalSpace()
            }
       }
    }
}





@Preview
@Composable
fun DropdownPreview() {




}