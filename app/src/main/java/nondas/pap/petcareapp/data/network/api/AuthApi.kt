package nondas.pap.petcareapp.data.network.api

import nondas.pap.petcareapp.data.network.ApiResponse
import nondas.pap.petcareapp.data.network.client.Payload
import nondas.pap.petcareapp.data.network.entity.AuthenticationResponse
import nondas.pap.petcareapp.data.network.entity.UserNetworkEntity
import nondas.pap.petcareapp.domain.entity.UserCredentials
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/v1/auth/authenticate")
    suspend fun login(@Body userCredentials: UserCredentials): ApiResponse<Payload<AuthenticationResponse>>

    @POST("/api/v1/auth/register")
    suspend fun register(@Body user: UserNetworkEntity): ApiResponse<Payload<Unit>>

}