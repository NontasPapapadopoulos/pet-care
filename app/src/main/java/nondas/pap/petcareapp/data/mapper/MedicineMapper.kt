package nondas.pap.petcareapp.data.mapper

import nondas.pap.petcareapp.data.entity.MedicineDataEntity
import nondas.pap.petcareapp.data.network.entity.MedicineNetworkEntity
import nondas.pap.petcareapp.domain.entity.MedicineDomainEntity

fun MedicineNetworkEntity.toData(): MedicineDataEntity = MedicineDataEntity(
    medicineId = id,
    type = type,
    repeatRate = repeatRate,
    dateReceived = dateReceived,
    comments = comments,
    petId = petId
)


fun MedicineDataEntity.toDomain(): MedicineDomainEntity = MedicineDomainEntity(
    type = type,
    repeatRate = repeatRate,
    dateReceived = dateReceived,
    comments = comments
)


fun MedicineDomainEntity.toData(): MedicineDataEntity = MedicineDataEntity(
    type = type,
    repeatRate = repeatRate,
    dateReceived = dateReceived,
    comments = comments,
    medicineId = medicineId,
    petId = petId
)


fun MedicineDataEntity.toNetwork(): MedicineNetworkEntity = MedicineNetworkEntity(
    id = medicineId,
    type = type,
    repeatRate = repeatRate,
    dateReceived = dateReceived,
    comments = comments,
    petId = petId
)
