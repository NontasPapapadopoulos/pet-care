package nondas.pap.petcareapp.presentation.medicine

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nondas.pap.petcareapp.R
import nondas.pap.petcareapp.domain.model.Medicine
import nondas.pap.petcareapp.domain.model.TimePeriod
import nondas.pap.petcareapp.presentation.ColorUtil
import nondas.pap.petcareapp.presentation.ColorUtil.Companion.getBorderColor
import nondas.pap.petcareapp.presentation.component.AddHorizontalSpace
import nondas.pap.petcareapp.presentation.component.AddVerticalSpace
import nondas.pap.petcareapp.presentation.component.Comments
import nondas.pap.petcareapp.presentation.component.MyDropdown
import nondas.pap.petcareapp.presentation.component.MyImage
import nondas.pap.petcareapp.presentation.component.MyText
import nondas.pap.petcareapp.presentation.component.MyTitle
import nondas.pap.petcareapp.presentation.component.PrimaryButton
import nondas.pap.petcareapp.presentation.component.SecondaryButton
import nondas.pap.petcareapp.presentation.component.inputText.InputText
import java.util.Date

@Composable
fun EditMedicineScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.mpez))
    ) {

        AddVerticalSpace(50)


        MyTitle(title = "petname medicine")


        AddVerticalSpace(20)


        MyDropdown(
            labelTitle = "Type",
            items = listOf(),
            selectedItem = "xx",
            onItemSelected = {},
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        AddVerticalSpace(15)

        MyDropdown(
            labelTitle = "Repeat when",
            items = listOf(),
            selectedItem = "xx",
            onItemSelected = {},
            modifier = Modifier.padding(20.dp, 0.dp)
        )


        AddVerticalSpace(15)

        MyText(
            text = "Comments",
            textAlignment = TextAlign.Start,
            modifier = Modifier.padding(start = 20.dp)
        )
        AddVerticalSpace(6)
        Comments(
            inputValue = "xx",
            valueEntered = {}
        )


        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            buttonTitle = "update",
            onButtonClicked = {},
            hasBorder = false
        )

        AddVerticalSpace()

        SecondaryButton(
            buttonTitle = "cancel",
            onButtonClicked = {},
            hasBorder = false
        )


        AddVerticalSpace(20)



    }


}





@Preview
@Composable
private fun AddMedicineScreenPreview() {
    EditMedicineScreen(navController = rememberNavController())
}