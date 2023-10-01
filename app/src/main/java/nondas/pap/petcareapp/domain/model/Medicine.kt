package nondas.pap.petcareapp.domain.model

import java.util.Date

data class Medicine(
    val type: String,
    val repeatRate: TimePeriod,
    val dateReceived: Date,
    val comments: String
)



enum class TimePeriod(val frequency: String) {
    EVERY_MONTH("every month"),
    EVERY_THREE_MONTHS("every three months"),
    EVERY_SIX_MONTHS("every six months"),
    EVERY_YEAR("every year")
}
