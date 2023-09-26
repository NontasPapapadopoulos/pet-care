package nondas.pap.petcareapp.infastracture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nondas.pap.petcareapp.infastracture.Cache
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {


    @Provides
    @Singleton
    fun provideCache(): Cache {
        return Cache()
    }
}