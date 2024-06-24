package nondas.pap.petcareapp.presentation.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nondas.pap.petcareapp.data.cache.AppDatabase
import nondas.pap.petcareapp.data.cache.dao.MedicineDao
import nondas.pap.petcareapp.data.cache.dao.PetDao
import nondas.pap.petcareapp.data.cache.dao.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun providesDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "pet-care_db")
            .fallbackToDestructiveMigration().build()
    }


    @Provides
    @Singleton
    fun providePetDao(database: AppDatabase): PetDao {
        return database.getPetDao()
    }

    @Provides
    @Singleton
    fun provideMedicineDao(database: AppDatabase): MedicineDao {
        return database.getMedicineDao()
    }


    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.getUserDao()
    }

}