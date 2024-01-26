package nondas.pap.petcareapp.domain.usecase.medicine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import nondas.pap.inventoryapp.domain.FlowUseCase
import nondas.pap.petcareapp.domain.repository.MedicineRepository
import nondas.pap.petcareapp.domain.executor.IoDispatcher
import nondas.pap.petcareapp.domain.entity.MedicineDomainEntity
import javax.inject.Inject

class GetMedicine @Inject constructor(
    private val medicineRepository: MedicineRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): FlowUseCase<List<MedicineDomainEntity>, GetMedicine.Params>(dispatcher) {


    override fun invoke(params: Params): Flow<List<MedicineDomainEntity>> {
        return medicineRepository.getMedicine(params.petId)
            .catch { emit(emptyList()) }
    }


    data class Params(val petId: Long)

}