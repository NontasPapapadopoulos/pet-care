package nondas.pap.petcareapp.domain.repository

import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.domain.entity.UserDomainEntity

interface UserRepository {
    fun getCurrentUserFlow(): Flow<UserDomainEntity>
    suspend fun getCurrentUser(): UserDomainEntity

}