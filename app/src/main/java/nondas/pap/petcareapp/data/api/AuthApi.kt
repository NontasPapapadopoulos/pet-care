package nondas.pap.petcareapp.data.api

import nondas.pap.petcareapp.domain.model.UserCredentials
import nondas.pap.petcareapp.domain.model.UserDetails
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {

    @POST("petCare/v1/auth/login")
    suspend fun login(@Body userCredentials: UserCredentials)


    @POST("petCare/v1/auth/register")
    suspend fun register(@Body user: UserDetails)
}