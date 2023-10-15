package nondas.pap.petcareapp.data.api

import nondas.pap.petcareapp.domain.model.Medicine
import nondas.pap.petcareapp.domain.model.Pet
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MedicineApi {

    @POST("petCare/v1/medicine/add")
    suspend fun addMedicine(@Body medicine: Medicine): Response<Unit>

    @PUT("petCare/v1/medicine/edit")
    suspend fun editMedicine(@Body medicine: Medicine): Response<Unit>

    @DELETE("petCare/v1/medicine/delete")
    suspend fun deleteMedicine(@Body medicine: Medicine): Response<Unit>

    @GET("petCare/v1/medicine/petId")
    suspend fun getMedicine(@Path("petId") petId: Long): Response<List<Medicine>>
}