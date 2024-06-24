package nondas.pap.petcareapp.presentation.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import nondas.pap.petcareapp.BuildConfig
import nondas.pap.petcareapp.data.network.api.AuthApi
import nondas.pap.petcareapp.data.network.api.PetCareApi
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
    fun providePetCareApi(retrofit: Retrofit): PetCareApi {
        return retrofit.create(PetCareApi::class.java)
    }



    @Singleton
    @Provides
    fun provideAuthApi(@Named("non-secure") retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
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

