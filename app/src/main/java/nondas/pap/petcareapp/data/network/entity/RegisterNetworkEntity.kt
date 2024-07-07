package nondas.pap.petcareapp.data.network.entity

import com.google.gson.annotations.SerializedName


data class RegisterNetworkEntity(
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String,
    @SerializedName("password") val password: String?
)
