package nondas.pap.petcareapp.domain.repository

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.domain.model.Pet
import retrofit2.Response

interface PetRepository {
    fun getPets(userId: Long): Flow<List<Pet>>

    suspend fun addPet(pet: Pet): Response<Unit>

    suspend fun editPet(pet: Pet): Response<Unit>

    suspend fun deletePet(pet: Pet): Response<Unit>

}