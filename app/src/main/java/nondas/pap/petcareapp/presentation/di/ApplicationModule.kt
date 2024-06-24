package nondas.pap.petcareapp.presentation.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import nondas.pap.petcareapp.domain.executor.IoDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @ExperimentalCoroutinesApi
    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO.limitedParallelism(4)


    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}
