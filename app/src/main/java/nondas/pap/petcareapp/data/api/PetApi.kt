package nondas.pap.petcareapp.data.api

import nondas.pap.petcareapp.domain.model.Pet
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PetApi {
    @POST("petCare/v1/pet/add")
    suspend fun addPet(@Body pet: Pet): Response<Unit>

    @PUT("petCare/v1/pet/edit")
    suspend fun editPet(@Body pet: Pet): Response<Unit>

    @DELETE("petCare/v1/pet/delete")
    suspend fun deletePet(@Body pet: Pet): Response<Unit>

    @GET("petCare/v1/pet/userId")
    suspend fun getPets(@Path("userId") userId: Long): Response<List<Pet>>
}