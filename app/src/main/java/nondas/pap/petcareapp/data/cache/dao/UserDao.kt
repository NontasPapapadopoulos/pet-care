package nondas.pap.petcareapp.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import nondas.pap.petcareapp.data.entity.UserDataEntity
import nondas.pap.petcareapp.domain.entity.UserCredentials


@Dao
interface UserDao {
//
//    @Query("SELECT * FROM user where email = :email and password = :password")
//    suspend fun login(email: String, password: String): UserDataEntity
//
//    @Insert
//    suspend fun addUser(userDataEntity: UserDataEntity)
//
//    @Delete
//    suspend fun logout()
}