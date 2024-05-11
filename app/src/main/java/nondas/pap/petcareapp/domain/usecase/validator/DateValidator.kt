package nondas.pap.petcareapp.domain.usecase.validator


import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DateValidator @Inject constructor() {

    private val DATE_FORMAT = "dd/MM/yyyy"

    enum class DATE_COMPARISON_FLAG {
        BEFORE_PRESENT_DATE, AFTER_PRESENT_DATE, NONE
    }


    fun execute(
        date: String,
        fieldName: String,
        comparisonFlag: DATE_COMPARISON_FLAG,
        comparisonDate: String = ""
    ): ValidationResult {

        if (isAValidDate(date)) {
            if (comparisonFlag == DATE_COMPARISON_FLAG.BEFORE_PRESENT_DATE)
                return getValidationResultForBeforePresentDates(date, comparisonDate)
            else if (comparisonFlag == DATE_COMPARISON_FLAG.AFTER_PRESENT_DATE)
                return getValidationResultForAfterPresentDates(date, comparisonDate)
            else
                return ValidationResult(
                    isError = true,
                )
        }


        return ValidationResult(
            isError = false,
            errorMessage = "Please enter a valid ${fieldName.lowercase()}"
        )

    }

    private fun getValidationResultForBeforePresentDates(date: String, comparisonDate: String): ValidationResult {
        if (isBeforePresentDate(date, comparisonDate)) {
            return ValidationResult(
                isError = true,
            )
        } else {
            return ValidationResult(
                isError = false,
                errorMessage = "Future dates are not accepted"
            )
        }
    }

    private fun getValidationResultForAfterPresentDates(date: String, comparisonDate: String): ValidationResult {
        if (isAfterPresentDate(date, comparisonDate)) {
            return ValidationResult(
                isError = true,
            )
        } else {
            return ValidationResult(
                isError = false,
                errorMessage = "Before present dates are not accepted"
            )
        }
    }


    private fun isAValidDate(inputDateString: String): Boolean {
        val simpleDateFormat: DateFormat =
            SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        simpleDateFormat.isLenient = false

        return try {
            simpleDateFormat.parse(inputDateString)
            true
        } catch (e: ParseException) {
            false
        }
    }


    private fun isAfterPresentDate(input: String, comparisonDate: String): Boolean {
        return isAfterOrBeforeDate(input, DATE_COMPARISON_FLAG.AFTER_PRESENT_DATE, comparisonDate)
    }



    private fun isBeforePresentDate(inputDate: String, comparisonDate: String): Boolean {
        return isAfterOrBeforeDate(inputDate, DATE_COMPARISON_FLAG.BEFORE_PRESENT_DATE, comparisonDate)
    }


    private fun isAfterOrBeforeDate(
        inputDate: String,
        date_comparison_flag: DATE_COMPARISON_FLAG,
        comparisonDate: String,
    ): Boolean {
        return try {
            val iDate =
                SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(inputDate)
            if (date_comparison_flag == DATE_COMPARISON_FLAG.AFTER_PRESENT_DATE)
                iDate.after(if(comparisonDate.isBlank()) Date() else SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(comparisonDate) )
            else
                iDate.before(if(comparisonDate.isBlank()) Date() else SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(comparisonDate) )
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


}