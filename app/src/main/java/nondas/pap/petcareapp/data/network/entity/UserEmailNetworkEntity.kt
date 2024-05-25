package nondas.pap.petcareapp.data.network.entity

import com.google.gson.annotations.SerializedName

data class UserEmailNetworkEntity(
    @SerializedName("email") val email: String
)
