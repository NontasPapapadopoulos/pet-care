package nondas.pap.petcareapp.infastracture.di

import android.content.Context
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
import nondas.pap.petcareapp.data.network.api.AuthApi
import nondas.pap.petcareapp.data.network.api.MedicineApi
import nondas.pap.petcareapp.data.network.api.PetApi
import nondas.pap.petcareapp.data.network.api.UserApi
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



    @Provides
    @Singleton
    fun provideUserDataSource(
        userDao: UserDao,
        authApi: AuthApi,
        userApi: UserApi,
    //    context: Context
    ): UserDataSource {
        return UserDataSourceImpl(
            userDao,
            authApi,
            userApi,
        //    context
        )
    }

}