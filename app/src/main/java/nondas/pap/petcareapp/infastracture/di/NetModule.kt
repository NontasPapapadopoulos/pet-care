package nondas.pap.petcareapp.infastracture.di


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import nondas.pap.petcareapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import nondas.pap.petcareapp.data.network.api.AuthApi
import nondas.pap.petcareapp.data.network.api.MedicineApi
import nondas.pap.petcareapp.data.network.api.PetApi
import nondas.pap.petcareapp.data.network.api.UserApi
import nondas.pap.petcareapp.data.repository.DataStorageRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {



    @Singleton
    @Provides
    fun provideSecureRetrofitInstance(
        authInterceptor: AuthInterceptor
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .callTimeout(1, TimeUnit.MINUTES)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    .addInterceptor(authInterceptor)
                .build()
            )
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    @Named("non-secure")
    fun provideRetrofitInstance(
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }


    @Singleton
    @Provides
    fun providePeApi(retrofit: Retrofit): PetApi {
        return retrofit.create(PetApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMedicineApi(retrofit: Retrofit): MedicineApi {
        return retrofit.create(MedicineApi::class.java)
    }


    @Singleton
    @Provides
    fun provideAuthApi(@Named("non-secure") retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }


    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }


}

class AuthInterceptor @Inject constructor(
    datastoreRepository: DataStorageRepository
): Interceptor{

    private val token = runBlocking { datastoreRepository.getToken() }
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        request.addHeader("Authorization", "Bearer $token")
        return chain.proceed(request.build())
    }


}

