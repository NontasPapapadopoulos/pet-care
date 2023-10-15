package nondas.pap.petcareapp.data.repository

import nondas.pap.petcareapp.domain.model.Pet
import retrofit2.Response

interface PetRepository {
    suspend fun getPets(userId: Long): Response<List<Pet>>

    suspend fun addPet(pet: Pet): Response<Unit>

    suspend fun editPet(pet: Pet): Response<Unit>

    suspend fun deletePet(pet: Pet): Response<Unit>

}