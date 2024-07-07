package nondas.pap.petcareapp.domain.repository

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.domain.entity.UserDomainEntity

interface UserRepository {
    fun getCurrentUser(): Flow<UserDomainEntity>

}