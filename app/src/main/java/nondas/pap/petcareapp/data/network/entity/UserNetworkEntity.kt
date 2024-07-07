package nondas.pap.petcareapp.data.network.entity

import com.google.gson.annotations.SerializedName
import nondas.pap.petcareapp.data.network.entity.PetNetworkEntity

data class UserNetworkEntity(
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("pets") var pets: List<PetNetworkEntity> = emptyList()
)
