package nondas.pap.petcareapp.domain.entity

import java.util.Date

data class MedicineDomainEntity(
    val type: String = "",
    val repeatRate: String = "" ,//TimePeriod = TimePeriod.EVERY_YEAR,
    val dateReceived: String = "", //Date = Date(),
    val comments: String = "",
    val medicineId: String = "0",
    val petId: String = "0"
)



enum class TimePeriod(val value: String) {
    EVERY_MONTH("every month"),
    EVERY_THREE_MONTHS("every three months"),
    EVERY_SIX_MONTHS("every six months"),
    EVERY_YEAR("every year")
}


enum class MedicineType(val type: String) {
    VACCINE("Vaccine"),
    PILL("Pill"),
    EXAMINATION("Examination"),
    OTHER("Other")
}
