package nondas.pap.petcareapp.domain.repository

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import nondas.pap.petcareapp.domain.entity.UserDomainEntity
import retrofit2.Response

interface UserRepository {
    fun getCurrentUser(): Flow<UserDomainEntity>

}