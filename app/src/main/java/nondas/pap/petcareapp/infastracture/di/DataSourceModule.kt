package nondas.pap.petcareapp.infastracture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nondas.pap.petcareapp.data.cache.dao.MedicineDao
import nondas.pap.petcareapp.data.cache.dao.PetDao
import nondas.pap.petcareapp.data.datasource.MedicineDataSource
import nondas.pap.petcareapp.data.datasource.MedicineDataSourceImpl
import nondas.pap.petcareapp.data.datasource.PetDataSource
import nondas.pap.petcareapp.data.datasource.PetDataSourceImpl
import nondas.pap.petcareapp.data.network.api.MedicineApi
import nondas.pap.petcareapp.data.network.api.PetApi
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {


    @Provides
    @Singleton
    fun providePetDataSource(petDao: PetDao, petApi: PetApi): PetDataSource {
        return PetDataSourceImpl(petDao, petApi)
    }

    @Provides
    @Singleton
    fun provideMedicineDataSource(medicineDao: MedicineDao, medicineApi: MedicineApi): MedicineDataSource {
        return MedicineDataSourceImpl(medicineDao, medicineApi)
    }



}