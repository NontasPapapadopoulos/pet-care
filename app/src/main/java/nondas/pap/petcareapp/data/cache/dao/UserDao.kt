package nondas.pap.petcareapp.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.data.entity.UserWithPets


@Dao
interface UserDao {

//    @Delete
//    suspend fun logout()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun put(user: UserDataEntity)

    @Query("SELECT * from user WHERE isCurrentUser = 1 LIMIT 1")
    fun getCurrentUserFlow(): Flow<UserDataEntity>

    @Query("SELECT * from user WHERE isCurrentUser = 1 LIMIT 1")
    suspend fun getCurrentUser(): UserDataEntity


    @Transaction
    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getUserWithPets(email: String): List<UserWithPets>

    @Query("UPDATE user set isCurrentUser = 0")
    suspend fun deselectUsers()
}