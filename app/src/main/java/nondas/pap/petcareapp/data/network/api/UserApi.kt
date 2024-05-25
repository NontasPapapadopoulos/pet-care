package nondas.pap.petcareapp.data.network.api

import nondas.pap.petcareapp.data.network.entity.UserEmailNetworkEntity
import nondas.pap.petcareapp.data.network.entity.UserNetworkEntity
import nondas.pap.petcareapp.domain.entity.UserCredentials
import nondas.pap.petcareapp.domain.entity.UserDomainEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/api/v1/user/")
    suspend fun getUser(@Body userEmailNetworkEntity: UserEmailNetworkEntity)
    
}