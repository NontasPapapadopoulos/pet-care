package nondas.pap.petcareapp.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.domain.entity.UserCredentials


@Dao
interface UserDao {
//
//    @Query("SELECT * FROM user where email = :email and password = :password")
//    suspend fun login(email: String, password: String): UserDataEntity


//    @Delete
//    suspend fun logout()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun put(user: UserDataEntity)

    @Query("SELECT * from user WHERE isCurrentUser = 1")
    fun getCurrentUser(): Flow<UserDataEntity>
}