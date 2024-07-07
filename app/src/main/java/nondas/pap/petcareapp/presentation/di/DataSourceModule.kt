package nondas.pap.petcareapp.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nondas.pap.petcareapp.data.cache.dao.MedicineDao
import nondas.pap.petcareapp.data.cache.dao.PetDao
import nondas.pap.petcareapp.data.cache.dao.UserDao
import nondas.pap.petcareapp.data.datasource.MedicineDataSource
import nondas.pap.petcareapp.data.datasource.MedicineDataSourceImpl
import nondas.pap.petcareapp.data.datasource.PetDataSource
import nondas.pap.petcareapp.data.datasource.PetDataSourceImpl
import nondas.pap.petcareapp.data.datasource.UserDataSource
import nondas.pap.petcareapp.data.datasource.UserDataSourceImpl
import nondas.pap.petcareapp.data.network.PetCareApi
import nondas.pap.petcareapp.data.repository.DataStorageRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {


    @Provides
    @Singleton
    fun providePetDataSource(petDao: PetDao, petCareApi: PetCareApi): PetDataSource {
        return PetDataSourceImpl(petDao, petCareApi)
    }

    @Provides
    @Singleton
    fun provideMedicineDataSource(medicineDao: MedicineDao, petCareApi: PetCareApi): MedicineDataSource {
        return MedicineDataSourceImpl(medicineDao, petCareApi)
    }



    @Provides
    @Singleton
    fun provideUserDataSource(
        userDao: UserDao,
        petDao: PetDao,
        petCareApi: PetCareApi,
        dataStorageRepository: DataStorageRepository
    ): UserDataSource {
        return UserDataSourceImpl(
            userDao,
            petDao,
            petCareApi,
            dataStorageRepository
        )
    }

}