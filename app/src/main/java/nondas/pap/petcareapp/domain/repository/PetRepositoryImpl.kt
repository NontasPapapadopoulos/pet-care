package nondas.pap.petcareapp.domain.repository

import nondas.pap.petcareapp.data.api.PetApi
import nondas.pap.petcareapp.data.repository.PetRepository
import nondas.pap.petcareapp.domain.model.Pet
import retrofit2.Response

class PetRepositoryImpl(
    private val petApi: PetApi
): PetRepository {
    override suspend fun getPets(userId: Long): Response<List<Pet>> {
        return petApi.getPets(userId = userId)
    }

    override suspend fun addPet(pet: Pet): Response<Unit> {
        return petApi.addPet(pet)
    }

    override suspend fun editPet(pet: Pet): Response<Unit> {
        return petApi.editPet(pet)
    }

    override suspend fun deletePet(pet: Pet): Response<Unit> {
        return petApi.deletePet(pet)
    }

}