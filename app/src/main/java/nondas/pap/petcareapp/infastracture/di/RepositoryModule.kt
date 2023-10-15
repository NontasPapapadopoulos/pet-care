package nondas.pap.petcareapp.infastracture.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nondas.pap.petcareapp.data.api.AuthApi
import nondas.pap.petcareapp.data.api.MedicineApi
import nondas.pap.petcareapp.data.api.PetApi
import nondas.pap.petcareapp.data.repository.AuthenticateRepository
import nondas.pap.petcareapp.data.repository.MedicineRepository
import nondas.pap.petcareapp.data.repository.PetRepository
import nondas.pap.petcareapp.domain.repository.AuthenticateRepositoryImpl
import nondas.pap.petcareapp.domain.repository.MedicineRepositoryImpl
import nondas.pap.petcareapp.domain.repository.PetRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideAuthorizationRepository(authApi: AuthApi): AuthenticateRepository {
        return AuthenticateRepositoryImpl(authApi)
    }


    @Provides
    @Singleton
    fun providePetRepository(petApi: PetApi): PetRepository {
        return PetRepositoryImpl(petApi)
    }

    @Provides
    @Singleton
    fun provideMedicineRepository(medicineApi: MedicineApi): MedicineRepository {
        return MedicineRepositoryImpl(medicineApi)
    }

}