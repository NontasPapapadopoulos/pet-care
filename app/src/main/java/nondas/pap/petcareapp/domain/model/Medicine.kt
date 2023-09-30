package nondas.pap.petcareapp.domain.model

import java.util.Date

data class Medicine(
    val type: String,
    val repeatEvery: TimePeriod,
    val dateReceived: Date
)



enum class TimePeriod {
    EVERY_MONTH,
    EVERY_THREE_MONTHS,
    EVERY_SIX_MONTHS,
    EVERY_YEAR
}
