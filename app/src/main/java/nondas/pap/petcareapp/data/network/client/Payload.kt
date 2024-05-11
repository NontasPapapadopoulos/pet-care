package nondas.pap.petcareapp.data.network.client

import com.google.gson.annotations.SerializedName

data class Payload<T>(
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: T?
)
