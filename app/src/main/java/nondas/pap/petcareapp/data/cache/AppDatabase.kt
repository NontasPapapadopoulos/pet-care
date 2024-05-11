package nondas.pap.petcareapp.data.cache

import androidx.room.Database

import androidx.room.RoomDatabase
import nondas.pap.petcareapp.data.cache.dao.MedicineDao
import nondas.pap.petcareapp.data.cache.dao.PetDao
import nondas.pap.petcareapp.data.cache.dao.UserDao

import nondas.pap.petcareapp.data.entity.MedicineDataEntity
import nondas.pap.petcareapp.data.entity.PetDataEntity
import nondas.pap.petcareapp.data.entity.UserDataEntity


@Database(
    entities = [
        MedicineDataEntity::class,
        PetDataEntity::class,
        UserDataEntity::class
    ],
    exportSchema = false,
    version = 1
)

abstract class AppDatabase(): RoomDatabase() {

    abstract fun getPetDao(): PetDao

    abstract fun getMedicineDao(): MedicineDao

    abstract fun getUserDao(): UserDao

}