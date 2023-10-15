package nondas.pap.petcareapp.presentation.util


import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import nondas.pap.petcareapp.R

class ColorUtil {

    companion object {

        fun getBorderColor(isFocused: Boolean, isValidationSuccessful: Boolean): Int {
            if (!isValidationSuccessful)
                return R.color.red

            return if (isFocused)
                R.color.purple
            else
                R.color.grey
        }



        fun getTextColor(isValidationSuccessful: Boolean): Int {
             if (isValidationSuccessful)
                 return R.color.grey
            return R.color.red
        }


        fun getBackgroundColor(isValidationSuccessful: Boolean): Int {
            if (isValidationSuccessful)
                return R.color.white
            return R.color.light_red

        }


        fun getPlaceHolderColor(isValidationSuccessful: Boolean): Int {
            if (isValidationSuccessful)
                return R.color.grey
            return R.color.red
        }




        fun getPurpleStatusBarColor(): Color {
            return Color(0xFF8D16C2)
        }


        fun getPurpleBackgroundColor() :Brush {
            val backgroundGradientColors = listOf(
                Color(0xFF8D16C2),  // Starting color
                Color(0xFFA71BBD),  // Middle color
                Color(0xFFC61A91)   // Ending color
            )

            return Brush.linearGradient(
                colors = backgroundGradientColors,
                start = Offset(0f, 0f),
                end = Offset.Infinite
            )
        }




    }
}