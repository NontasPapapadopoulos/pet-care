package nondas.pap.petcareapp.presentation.component.inputText

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nondas.pap.petcareapp.presentation.util.ColorUtil.Companion.getPlaceHolderColor

import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.presentation.component.MyPlaceholder


@Composable
fun MyBasicTextField(
    inputText: String,
    onValueChanged: (String) -> Unit,
    isValidationSuccessful: Boolean,
    isNumberPad: Boolean,
    placeHolder: String,
    fontSize: Int,
    fontWeight: FontWeight = FontWeight.W400,
    visualTransformation: VisualTransformation,
    modifier: Modifier = Modifier
) {

    BasicTextField(
        value = inputText,
        onValueChange =  { onValueChanged(it) } ,
        modifier = modifier.width(IntrinsicSize.Max),
        textStyle = TextStyle(
            color = colorResource(id = R.color.black),
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
        ),
        cursorBrush = SolidColor(Color.Black),
        singleLine = true,
        decorationBox = { innerTextField ->
            if (inputText.isEmpty()) {
                MyPlaceholder(
                    placeholder = placeHolder,
                    color = getPlaceHolderColor(isValidationSuccessful)
                )
            }
            innerTextField()
        },
        visualTransformation = visualTransformation,
        keyboardOptions = if (isNumberPad) {
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        }
        else {
            KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        }
    )
}

@Preview
@Composable
private fun MyBasicTextFieldPreview() {

    val text = remember { mutableStateOf("asdsada") }

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(colorResource(id = R.color.white))) {

        MyBasicTextField(
            inputText = text.value,
            onValueChanged = { newText ->
                text.value = newText
            },
            isValidationSuccessful = true,
            isNumberPad = false,
            placeHolder = "placeholder",
            fontSize = 16,
            fontWeight = FontWeight.W400,
            visualTransformation = VisualTransformation.None,
        )
    }
}