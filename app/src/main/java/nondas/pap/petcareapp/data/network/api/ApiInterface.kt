package nondas.pap.petcareapp.data.network.api

import androidx.datastore.preferences.protobuf.Api
import nondas.pap.petcareapp.data.network.client.response.Payload
import nondas.pap.petcareapp.data.network.entity.UserNetworkEntity
import nondas.pap.petcareapp.data.network.entity.AuthenticationResponse
import nondas.pap.petcareapp.data.network.entity.MedicineNetworkEntity
import nondas.pap.petcareapp.data.network.entity.PetNetworkEntity
import nondas.pap.petcareapp.data.network.entity.RegisterNetworkEntity
import nondas.pap.petcareapp.domain.entity.UserCredentials
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @POST("/api/v1/auth/authenticate")
    suspend fun login(@Body userCredentials: UserCredentials): ApiResponse<Payload<AuthenticationResponse>>

    @POST("/api/v1/auth/register")
    suspend fun register(@Body register: RegisterNetworkEntity): ApiResponse<Payload<Unit>>

    @GET("/api/v1/user/")
    suspend fun getUser(@Query("email") email: String): ApiResponse<Payload<UserNetworkEntity>>

    @GET("petCare/v1/pet/userId")
    suspend fun getPets(@Path("userId") userId: Long): ApiResponse<Payload<List<PetNetworkEntity>>>

    @POST("petCare/v1/pet/add")
    suspend fun addPet(@Body petNetworkEntity: PetNetworkEntity): ApiResponse<Payload<Unit>>

    @PUT("petCare/v1/pet/edit")
    suspend fun editPet(@Body petNetworkEntity: PetNetworkEntity): ApiResponse<Payload<Unit>>

    @DELETE("petCare/v1/pet/delete")
    suspend fun deletePet(@Body petNetworkEntity: PetNetworkEntity): ApiResponse<Payload<Unit>>

    @GET("petCare/v1/medicine/petId")
    suspend fun getMedicine(@Path("petId") petId: Long): ApiResponse<Payload<List<MedicineNetworkEntity>>>

    @POST("petCare/v1/medicine/add")
    suspend fun addMedicine(@Body medicineNetworkEntity: MedicineNetworkEntity): ApiResponse<Payload<Unit>>

    @PUT("petCare/v1/medicine/edit")
    suspend fun editMedicine(@Body medicineNetworkEntity: MedicineNetworkEntity): ApiResponse<Payload<Unit>>

    @DELETE("petCare/v1/medicine/delete")
    suspend fun deleteMedicine(@Body medicineNetworkEntity: MedicineNetworkEntity): ApiResponse<Payload<Unit>>


}

















