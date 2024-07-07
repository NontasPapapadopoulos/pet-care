package nondas.pap.petcareapp.data.network.api

import nondas.pap.petcareapp.data.network.client.response.ServiceProvider
import nondas.pap.petcareapp.data.network.entity.MedicineNetworkEntity
import nondas.pap.petcareapp.data.network.entity.PetNetworkEntity
import nondas.pap.petcareapp.data.network.entity.RegisterNetworkEntity
import nondas.pap.petcareapp.domain.entity.UserCredentials
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApiService @Inject constructor(
    private val provider: ServiceProvider<ApiInterface>
) {

    private fun getApi() = provider.getService()

    suspend fun login(userCredentials: UserCredentials) = getApi().login(userCredentials)

    suspend fun register(user: RegisterNetworkEntity) = getApi().register(user)

    suspend fun getUser(email: String) = getApi().getUser(email)

    suspend fun getPets(userId: Long) = getApi().getPets(userId)

    suspend fun addPet(petNetworkEntity: PetNetworkEntity) = getApi().addPet(petNetworkEntity)

    suspend fun editPet(petNetworkEntity: PetNetworkEntity) = getApi().editPet(petNetworkEntity)
    suspend fun deletePet(petNetworkEntity: PetNetworkEntity) = getApi().deletePet(petNetworkEntity)

    suspend fun getMedicine(petId: Long) = getApi().getMedicine(petId)
    suspend fun addMedicine(medicineNetworkEntity: MedicineNetworkEntity) = getApi().addMedicine(medicineNetworkEntity)
    suspend fun editMedicine(medicineNetworkEntity: MedicineNetworkEntity) = getApi().editMedicine(medicineNetworkEntity)
    suspend fun deleteMedicine(medicineNetworkEntity: MedicineNetworkEntity) = getApi().deleteMedicine(medicineNetworkEntity)































}