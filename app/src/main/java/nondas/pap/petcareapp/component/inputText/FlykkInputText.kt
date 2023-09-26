package nondas.pap.petcareapp.component.inputText

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nondas.pap.petcareapp.ColorUtil.Companion.getBackgroundColor
import nondas.pap.petcareapp.ColorUtil.Companion.getBorderColor
import nondas.pap.petcareapp.ColorUtil.Companion.getTextColor
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.component.AddVerticalSpace
import nondas.pap.petcareapp.component.ErrorMessage
import nondas.pap.petcareapp.component.FieldLabel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FlykkInputText(
    inputValue: String,
    valueEntered: (String) -> Unit,
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight(400),
    labelTitle: String = "",
    errorMessage: String = "",
    placeholder: String = "",
    isValidationSuccessful: Boolean = true,
    hasImageStart: Boolean = false,
    startImageHasVerticalLine: Boolean = true,
    imageStartId: Int? = null,
    imageStartWidth: Int? = null,
    imageStartHeight: Int? = null,
    hasImageEnd: Boolean = false,
    endImageHasVerticalLine: Boolean = true,
    imageEndId: Int? = null,
    imageEndWidth: Int? = null,
    imageEndHeight: Int? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isNumberPad: Boolean = false,
    modifier: Modifier = Modifier
) {

    val isFocused = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
                .fillMaxWidth()
    ) {

        if (labelTitle.isNotEmpty()) {
            FieldLabel(
                labelTitle = labelTitle,
                color = getTextColor(isValidationSuccessful)
            )
            AddVerticalSpace(6)
        }


        Row(
            modifier = Modifier
                .height(46.dp)
                .fillMaxWidth()
                .focusRequester(FocusRequester())
                .onFocusChanged { isFocused.value = it.isFocused }
                .background(
                    color = colorResource(id = getBackgroundColor(isValidationSuccessful)),
                    shape = RoundedCornerShape(size = 36.dp)
                )
                .border(
                    width = if (isFocused.value) 2.dp else 1.dp,
                    color = colorResource(
                        id = getBorderColor(
                                isFocused = isFocused.value,
                                isValidationSuccessful)
                    ),
                    shape = RoundedCornerShape(size = 36.dp)
                )
                .padding(14.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.weight(0.9f)
                    .focusRequester(FocusRequester())
                    .onFocusChanged { isFocused.value = it.isFocused },
                verticalAlignment = Alignment.CenterVertically,
            ) {

                if (hasImageStart) {
                    PairImagesStart(
                        startImageId = imageStartId!!,
                        startImageHeight = imageStartHeight!!,
                        startImageWidth = imageStartWidth!!,
                        startImageHasVerticalLine = startImageHasVerticalLine
                    )
                }

                MyBasicTextField(
                    inputText = inputValue,
                    onValueChanged = { valueEntered(it) },
                    isValidationSuccessful = isValidationSuccessful,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    placeHolder = placeholder,
                    visualTransformation = visualTransformation,
                    isNumberPad = isNumberPad,
                    modifier = Modifier.fillMaxWidth()
                        .weight(0.8f)
                )
            }


            if (hasImageEnd) {
                PairImagesEnd(
                    imageEndId = imageEndId!!,
                    imageEndWidth = imageEndWidth!!,
                    imageEndHeight = imageEndHeight!!,
                    endImageHasVerticalLine = endImageHasVerticalLine,
                   modifier =  modifier.weight(0.1f)
                )
            }
        }

        if (!isValidationSuccessful && errorMessage.isNotEmpty()) {
            ErrorMessage(errorMessage = errorMessage)
        }

    }

}


@Preview
@Composable
private fun InputTextPreview() {
    FlykkInputText(
        inputValue = "",
        valueEntered = { },
        fontSize = 20,
        fontWeight = FontWeight(400),
        labelTitle = "My label",
        errorMessage = " error message ",
        placeholder = "this is my input text2",
        hasImageStart = true,
        hasImageEnd = true,
        isValidationSuccessful = true,
        imageStartId = R.drawable.ic_launcher_background,
        imageStartWidth = 20,
        imageStartHeight = 20,
        imageEndId = R.drawable.ic_launcher_background,
        imageEndWidth = 20,
        imageEndHeight = 20,
    )
}