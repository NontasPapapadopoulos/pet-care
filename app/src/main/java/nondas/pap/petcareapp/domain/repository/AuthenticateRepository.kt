package nondas.pap.petcareapp.domain.repository

import nondas.pap.petcareapp.domain.entity.UserCredentials
import nondas.pap.petcareapp.domain.entity.UserDomainEntity

interface AuthenticateRepository {

    suspend fun login(userCredentials: UserCredentials)

    suspend fun register(userDomainEntity: UserDomainEntity)



    
}