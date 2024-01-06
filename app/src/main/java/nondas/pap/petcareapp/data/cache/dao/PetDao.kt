package nondas.pap.petcareapp.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.entity.PetDataEntity


@Dao
interface PetDao {

    @Query("SELECT * FROM pet")
    fun getPets(): Flow<List<PetDataEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addPet(pet: PetDataEntity)

    @Update
    suspend fun updatePet(pet: PetDataEntity)

    @Delete
    suspend fun deletePet(pet: PetDataEntity)
}