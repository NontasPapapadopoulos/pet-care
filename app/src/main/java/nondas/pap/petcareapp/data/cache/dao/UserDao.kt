package nondas.pap.petcareapp.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.data.entity.UserWithPets
import nondas.pap.petcareapp.domain.entity.UserCredentials


@Dao
interface UserDao {

//    @Delete
//    suspend fun logout()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun put(user: UserDataEntity)

    @Query("SELECT * from user WHERE isCurrentUser = 1")
    fun getCurrentUser(): Flow<UserDataEntity>

    @Transaction
    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getUserWithPets(email: String): List<UserWithPets>
}