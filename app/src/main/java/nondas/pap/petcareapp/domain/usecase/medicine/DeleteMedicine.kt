package nondas.pap.petcareapp.domain.usecase.medicine

import kotlinx.coroutines.CoroutineDispatcher
import nondas.pap.inventoryapp.domain.SuspendUseCase
import nondas.pap.petcareapp.domain.repository.MedicineRepository
import nondas.pap.petcareapp.domain.executor.IoDispatcher
import nondas.pap.petcareapp.domain.entity.MedicineDomainEntity
import javax.inject.Inject

class DeleteMedicine @Inject constructor(
    private val medicineRepository: MedicineRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): SuspendUseCase<Unit, DeleteMedicine.Params>(dispatcher) {

    override suspend fun invoke(params: Params) {
        medicineRepository.deleteMedicine(params.medicineDomainEntity)
    }
    data class Params(val medicineDomainEntity: MedicineDomainEntity)


}