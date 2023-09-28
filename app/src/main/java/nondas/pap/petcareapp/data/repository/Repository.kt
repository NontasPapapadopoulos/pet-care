package nondas.pap.petcareapp.data.repository

import retrofit2.http.GET
import retrofit2.http.POST

interface Repository {

    suspend fun login()

    suspend fun register()

    suspend fun addPet()

    suspend fun getPets()
}