package nondas.pap.petcareapp.data.network.entity

import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(
    @SerializedName("token") val token: String
)
