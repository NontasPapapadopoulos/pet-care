package nondas.pap.petcareapp.data.network.api

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.domain.entity.MedicineDomainEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MedicineApi {

    @GET("petCare/v1/medicine/petId")
    fun getMedicine(@Path("petId") petId: Long): Flow<List<MedicineDomainEntity>>

    @POST("petCare/v1/medicine/add")
    suspend fun addMedicine(@Body medicineDomainEntity: MedicineDomainEntity): Response<Unit>

    @PUT("petCare/v1/medicine/edit")
    suspend fun editMedicine(@Body medicineDomainEntity: MedicineDomainEntity): Response<Unit>

    @DELETE("petCare/v1/medicine/delete")
    suspend fun deleteMedicine(@Body medicineDomainEntity: MedicineDomainEntity): Response<Unit>


}