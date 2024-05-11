package nondas.pap.petcareapp.infastracture.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nondas.pap.petcareapp.data.datasource.MedicineDataSource
import nondas.pap.petcareapp.data.datasource.PetDataSource
import nondas.pap.petcareapp.data.datasource.UserDataSource
import nondas.pap.petcareapp.data.repository.AuthenticateDataRepository
import nondas.pap.petcareapp.data.repository.MedicineDataRepository
import nondas.pap.petcareapp.data.repository.PetDataRepository
import nondas.pap.petcareapp.data.repository.UserDataRepository
import nondas.pap.petcareapp.domain.repository.AuthenticateRepository
import nondas.pap.petcareapp.domain.repository.MedicineRepository
import nondas.pap.petcareapp.domain.repository.PetRepository
import nondas.pap.petcareapp.domain.repository.UserRepository
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
        return PetDataRepository(petDataSource)
    }

    @Provides
    @Singleton
    fun provideMedicineRepository(medicineDataSource: MedicineDataSource): MedicineRepository {
        return MedicineDataRepository(medicineDataSource)
    }


    @Provides
    @Singleton
    fun provideUserRepository(userDataSource: UserDataSource): UserRepository {
        return UserDataRepository(userDataSource)
    }

}