package nondas.pap.petcareapp.data.network

import nondas.pap.petcareapp.data.exception.EmptyResponseException
import nondas.pap.petcareapp.data.exception.InvalidPayloadException
import nondas.pap.petcareapp.data.exception.PayloadException
import nondas.pap.petcareapp.data.exception.ResponseException
import nondas.pap.petcareapp.data.network.api.ApiEmptyResponse
import nondas.pap.petcareapp.data.network.api.ApiErrorResponse
import nondas.pap.petcareapp.data.network.api.ApiResponse
import nondas.pap.petcareapp.data.network.api.ApiService
import nondas.pap.petcareapp.data.network.api.ApiSuccessResponse
import nondas.pap.petcareapp.data.network.client.response.Payload
import nondas.pap.petcareapp.data.network.entity.UserNetworkEntity
import nondas.pap.petcareapp.data.network.entity.AuthenticationResponse
import nondas.pap.petcareapp.data.network.entity.MedicineNetworkEntity
import nondas.pap.petcareapp.data.network.entity.PetNetworkEntity
import nondas.pap.petcareapp.data.network.entity.RegisterNetworkEntity
import nondas.pap.petcareapp.domain.entity.UserCredentials
import javax.inject.Inject

interface PetCareApi {
    suspend fun login(userCredentials: UserCredentials): AuthenticationResponse
    suspend fun register(register: RegisterNetworkEntity)
    suspend fun getUser(email: String): UserNetworkEntity
    suspend fun getPets(userId: Long): List<PetNetworkEntity>
    suspend fun addPet(petNetworkEntity: PetNetworkEntity)
    suspend fun editPet(petNetworkEntity: PetNetworkEntity)
    suspend fun deletePet(petNetworkEntity: PetNetworkEntity)
    suspend fun getMedicine(petId: Long): List<MedicineNetworkEntity>
    suspend fun addMedicine(medicineNetworkEntity: MedicineNetworkEntity)
    suspend fun editMedicine(medicineNetworkEntity: MedicineNetworkEntity)
    suspend fun deleteMedicine(medicineNetworkEntity: MedicineNetworkEntity)
}


class JsonPetCareApi @Inject constructor(
    private val apiService: ApiService
): PetCareApi {
    override suspend fun login(userCredentials: UserCredentials): AuthenticationResponse {
        return apiService.login(userCredentials).unpack()
    }

    override suspend fun register(register: RegisterNetworkEntity) {
        apiService.register(register).ensureSuccess()
    }

    override suspend fun getUser(email: String): UserNetworkEntity {
        return apiService.getUser(email).unpack()
    }

    override suspend fun getPets(userId: Long): List<PetNetworkEntity> {
        return apiService.getPets(userId).unpack()
    }

    override suspend fun addPet(petNetworkEntity: PetNetworkEntity) {
        apiService.addPet(petNetworkEntity).ensureSuccess()
    }

    override suspend fun editPet(petNetworkEntity: PetNetworkEntity) {
        apiService.editPet(petNetworkEntity).ensureSuccess()
    }

    override suspend fun deletePet(petNetworkEntity: PetNetworkEntity) {
        apiService.deletePet(petNetworkEntity).ensureSuccess()
    }

    override suspend fun getMedicine(petId: Long): List<MedicineNetworkEntity> {
        return apiService.getMedicine(petId).unpack()
    }

    override suspend fun addMedicine(medicineNetworkEntity: MedicineNetworkEntity) {
        apiService.addMedicine(medicineNetworkEntity).ensureSuccess()
    }

    override suspend fun editMedicine(medicineNetworkEntity: MedicineNetworkEntity) {
        apiService.editMedicine(medicineNetworkEntity).ensureSuccess()
    }

    override suspend fun deleteMedicine(medicineNetworkEntity: MedicineNetworkEntity) {
        apiService.deleteMedicine(medicineNetworkEntity).ensureSuccess()
    }



    private fun <T> ApiResponse<Payload<T>>.unpack(): T {
        return when (this) {
            is ApiEmptyResponse<Payload<T>> -> throw EmptyResponseException()
            is ApiErrorResponse<Payload<T>> -> throw ResponseException(errorCode, errorMessages)
            is ApiSuccessResponse<Payload<T>> -> {
                when {
                    body.message.isNotEmpty() && body.data == null -> throw PayloadException(body.message)

                    body.data == null -> throw InvalidPayloadException()
                    else -> body.data
                }
            }
        }
    }

    private fun <T> ApiResponse<Payload<T>>.ensureSuccess() {
        when (this) {
            is ApiEmptyResponse<Payload<T>> -> throw EmptyResponseException()
            is ApiErrorResponse<Payload<T>> -> throw ResponseException(errorCode, errorMessages)
            is ApiSuccessResponse<Payload<T>> -> {
            }
        }
    }

}
























