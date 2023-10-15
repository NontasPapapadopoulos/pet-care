package nondas.pap.petcareapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.presentation.util.ColorUtil

@Composable
fun Comments(
    inputValue: String,
    valueEntered: (String) -> Unit,
) {

    Column {
        MyText(
            text = "Comments",
            color = R.color.grey,
            textAlignment = TextAlign.Start,
            modifier = Modifier.padding(start = 20.dp)
        )
        AddVerticalSpace(6)

        val isFocused = remember { mutableStateOf(false) }

        BasicTextField(
            value = inputValue,
            onValueChange = { valueEntered(it) },
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .focusRequester(FocusRequester())
                .onFocusChanged { isFocused.value = it.isFocused }
                .fillMaxWidth()
                .padding(20.dp, 0.dp)
                .height(150.dp)
                .background(
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .border(
                    width = if (isFocused.value) 2.dp else 1.dp,
                    color = colorResource(
                        id = ColorUtil.getBorderColor(
                            isFocused = isFocused.value,
                            true
                        )
                    ),
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .padding(10.dp)
        )

    }


}


@Composable
@Preview
private fun CommentsPreview() {
    Comments(
        inputValue = "xxx",
        valueEntered = {}
    )
}