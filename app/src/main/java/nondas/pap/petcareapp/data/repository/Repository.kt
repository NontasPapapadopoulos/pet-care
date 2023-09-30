package nondas.pap.petcareapp.data.repository

import nondas.pap.petcareapp.domain.model.Pet
import nondas.pap.petcareapp.domain.model.UserCredentials
import nondas.pap.petcareapp.domain.model.UserDetails
import retrofit2.http.GET
import retrofit2.http.POST

interface Repository {

    suspend fun login(userCredentials: UserCredentials)

    suspend fun register(userDetails: UserDetails)

    suspend fun addPet(pet: Pet)

    suspend fun getPets(): List<Pet>
}