package nondas.pap.petcareapp.domain.repository

import nondas.pap.petcareapp.domain.model.Pet
import nondas.pap.petcareapp.domain.model.UserCredentials
import nondas.pap.petcareapp.domain.model.UserDetails

interface AuthenticateRepository {

    suspend fun login(userCredentials: UserCredentials)

    suspend fun register(userDetails: UserDetails)



    
}