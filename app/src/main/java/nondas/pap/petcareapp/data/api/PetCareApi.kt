package nondas.pap.petcareapp.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PetCareApi {


    @POST()
    suspend fun login()

    @POST()
    suspend fun register()

    @POST()
    suspend fun addPet()

    @GET()
    suspend fun getPets()


}