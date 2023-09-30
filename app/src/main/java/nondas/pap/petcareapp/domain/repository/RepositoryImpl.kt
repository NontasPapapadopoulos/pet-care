package nondas.pap.petcareapp.domain.repository

import nondas.pap.petcareapp.data.repository.Repository
import nondas.pap.petcareapp.domain.model.Pet
import nondas.pap.petcareapp.domain.model.UserCredentials
import nondas.pap.petcareapp.domain.model.UserDetails

class RepositoryImpl: Repository {
    override suspend fun login(userCredentials: UserCredentials) {
        TODO("Not yet implemented")
    }

    override suspend fun register(userDetails: UserDetails) {
        TODO("Not yet implemented")
    }

    override suspend fun addPet(pet: Pet) {
        TODO("Not yet implemented")
    }

    override suspend fun getPets(): List<Pet> {
        TODO("Not yet implemented")
    }


}