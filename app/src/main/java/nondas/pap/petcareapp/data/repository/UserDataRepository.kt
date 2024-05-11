package nondas.pap.petcareapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nondas.pap.petcareapp.data.datasource.UserDataSource
import nondas.pap.petcareapp.data.mapper.toDomain
import nondas.pap.petcareapp.domain.entity.UserDomainEntity
import nondas.pap.petcareapp.domain.repository.UserRepository

class UserDataRepository(
    private val userDataSource: UserDataSource
): UserRepository {

    override fun getCurrentUser(): Flow<UserDomainEntity> {
       return userDataSource.getUser().map { it.toDomain() }
    }


}