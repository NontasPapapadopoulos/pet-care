package nondas.pap.petcareapp.infastracture.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nondas.pap.petcareapp.data.datasource.MedicineDataSource
import nondas.pap.petcareapp.data.datasource.PetDataSource
import nondas.pap.petcareapp.data.datasource.UserDataSource
import nondas.pap.petcareapp.data.network.api.AuthApi
import nondas.pap.petcareapp.data.repository.AuthenticateDataRepository
import nondas.pap.petcareapp.data.repository.MedicineDataRepository
import nondas.pap.petcareapp.data.repository.PetRepositoryImpl
import nondas.pap.petcareapp.domain.repository.AuthenticateRepository
import nondas.pap.petcareapp.domain.repository.MedicineRepository
import nondas.pap.petcareapp.domain.repository.PetRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideAuthorizationRepository(userDataSource: UserDataSource): AuthenticateRepository {
        return AuthenticateDataRepository(userDataSource)
    }


    @Provides
    @Singleton
    fun providePetRepository(petDataSource: PetDataSource): PetRepository {
        return PetRepositoryImpl(petDataSource)
    }

    @Provides
    @Singleton
    fun provideMedicineRepository(medicineDataSource: MedicineDataSource): MedicineRepository {
        return MedicineDataRepository(medicineDataSource)
    }

}