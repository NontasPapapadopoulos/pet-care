package nondas.pap.petcareapp.data.network.api

import nondas.pap.petcareapp.domain.entity.UserCredentials
import nondas.pap.petcareapp.domain.entity.UserDomainEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("petCare/v1/auth/login")
    suspend fun login(@Body userCredentials: UserCredentials)


    @POST("petCare/v1/auth/register")
    suspend fun register(@Body user: UserDomainEntity)
}