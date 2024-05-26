package nondas.pap.petcareapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStorageRepository(
    private val context: Context
) {

    companion object {
        val TOKEN_KEY = stringPreferencesKey("auth_token")
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")
    }


    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun getToken(): String? {
        return context.dataStore.data
            .map { preferences ->
                preferences[TOKEN_KEY]
            }.first()

    }

}