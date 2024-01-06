package nondas.pap.petcareapp.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import nondas.pap.petcareapp.data.entity.MedicineDataEntity
import nondas.pap.petcareapp.data.entity.PetDataEntity


@Dao
interface MedicineDao {

    @Query("SELECT * FROM medicine")
    fun getMedicines(): Flow<List<MedicineDataEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addMedicine(medicine: MedicineDataEntity)

    @Update
    suspend fun updateMedicine(medicine: MedicineDataEntity)

    @Delete
    suspend fun deleteMedicine(medicine: MedicineDataEntity)
}