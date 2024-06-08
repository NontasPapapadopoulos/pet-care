package nondas.pap.petcareapp.data.network.dto

import com.google.gson.annotations.SerializedName
import nondas.pap.petcareapp.data.network.entity.PetNetworkEntity

data class UserResponseDto(
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String,
    @SerializedName("pets") var pets: List<PetNetworkEntity> = emptyList()
)
