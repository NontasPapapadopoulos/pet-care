package nondas.pap.petcareapp.data.datasource


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.cache.dao.UserDao
import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.data.mapper.toNetwork
import nondas.pap.petcareapp.data.network.api.AuthApi
import nondas.pap.petcareapp.data.network.api.UserApi
import nondas.pap.petcareapp.data.network.entity.UserEmailNetworkEntity
import nondas.pap.petcareapp.domain.entity.UserCredentials

interface UserDataSource {
    suspend fun login(userCredentials: UserCredentials)
    suspend fun register(userDataEntity: UserDataEntity)
    suspend fun logout()
    fun getUser(): Flow<UserDataEntity>

}

private val TOKEN_KEY = stringPreferencesKey("auth_token")
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

class UserDataSourceImpl(
    private val userDao: UserDao,
    private val authApi: AuthApi,
    private val userApi: UserApi,
    private val context: Context,
): UserDataSource {
    override suspend fun login(userCredentials: UserCredentials) {
        val token = authApi.login(userCredentials).token
        saveToken(token)
        val user = userApi.getUser(UserEmailNetworkEntity(userCredentials.email))

        // store the user to dao
        // TODO: get the user details, store them in the local db. get the user`s pets and pet`s medicine's
    }

    override suspend fun register(userDataEntity: UserDataEntity) {
        authApi.register(userDataEntity.toNetwork())

    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override fun getUser(): Flow<UserDataEntity> {
        return userDao.getCurrentUser()
    }


    private suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

}

