package nondas.pap.petcareapp.data.network.api

import nondas.pap.petcareapp.data.network.ApiResponse
import nondas.pap.petcareapp.data.network.client.Payload
import nondas.pap.petcareapp.data.network.dto.UserResponseDto
import nondas.pap.petcareapp.data.network.entity.AuthenticationResponse
import nondas.pap.petcareapp.data.network.entity.UserNetworkEntity
import nondas.pap.petcareapp.domain.entity.MedicineDomainEntity
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import nondas.pap.petcareapp.domain.entity.UserCredentials
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PetCareApi {
    @POST("/api/v1/auth/authenticate")
    suspend fun login(@Body userCredentials: UserCredentials): ApiResponse<Payload<AuthenticationResponse>>

    @POST("/api/v1/auth/register")
    suspend fun register(@Body user: UserNetworkEntity): ApiResponse<Payload<Unit>>

    @GET("/api/v1/user/")
    suspend fun getUser(@Query("email") email: String): Response<UserResponseDto>

    @GET("petCare/v1/pet/userId")
    fun getPets(@Path("userId") userId: Long): Response<List<PetDomainEntity>>

    @POST("petCare/v1/pet/add")
    suspend fun addPet(@Body petDomainEntity: PetDomainEntity): Response<Unit>

    @PUT("petCare/v1/pet/edit")
    suspend fun editPet(@Body petDomainEntity: PetDomainEntity): Response<Unit>

    @DELETE("petCare/v1/pet/delete")
    suspend fun deletePet(@Body petDomainEntity: PetDomainEntity): Response<Unit>

    @GET("petCare/v1/medicine/petId")
    fun getMedicine(@Path("petId") petId: Long): Response<List<MedicineDomainEntity>>

    @POST("petCare/v1/medicine/add")
    suspend fun addMedicine(@Body medicineDomainEntity: MedicineDomainEntity): Response<Unit>

    @PUT("petCare/v1/medicine/edit")
    suspend fun editMedicine(@Body medicineDomainEntity: MedicineDomainEntity): Response<Unit>

    @DELETE("petCare/v1/medicine/delete")
    suspend fun deleteMedicine(@Body medicineDomainEntity: MedicineDomainEntity): Response<Unit>


}