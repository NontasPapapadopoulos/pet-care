package nondas.pap.petcareapp.infastracture.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nondas.pap.petcareapp.data.network.api.AuthApi
import nondas.pap.petcareapp.data.network.api.MedicineApi
import nondas.pap.petcareapp.data.network.api.PetApi
import nondas.pap.petcareapp.data.repository.AuthenticateRepositoryImpl
import nondas.pap.petcareapp.data.repository.MedicineRepositoryImpl
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