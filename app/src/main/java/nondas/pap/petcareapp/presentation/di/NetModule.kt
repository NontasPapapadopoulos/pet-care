package nondas.pap.petcareapp.presentation.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nondas.pap.petcareapp.data.network.JsonPetCareApi
import nondas.pap.petcareapp.data.network.PetCareApi
import nondas.pap.petcareapp.data.network.api.ApiInterface
import nondas.pap.petcareapp.data.network.client.response.RetrofitInstanceProvider
import nondas.pap.petcareapp.data.network.client.response.ServiceProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {


    @Singleton
    @Provides
    fun provideApi(jsonPetCareApi: JsonPetCareApi): PetCareApi = jsonPetCareApi



    @Singleton
    @Provides
    fun provideApiService(serviceProvider: RetrofitInstanceProvider): ServiceProvider<ApiInterface> {
        return serviceProvider
    }
}

//
//
//    @Singleton
//    @Provides
//    fun provideSecureRetrofitInstance(
//        authInterceptor: AuthInterceptor
//    ): Retrofit {
//        return Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(
//                OkHttpClient.Builder()
//                    .callTimeout(1, TimeUnit.MINUTES)
//                    .connectTimeout(60, TimeUnit.SECONDS)
//                    .writeTimeout(60, TimeUnit.SECONDS)
//                    .readTimeout(60, TimeUnit.SECONDS)
//                    .addInterceptor(
//                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//                    )
//                    .addInterceptor(authInterceptor)
//                .build()
//            )
//            .baseUrl(BuildConfig.BASE_URL)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    @Named("non-secure")
//    fun provideRetrofitInstance(
//    ): Retrofit {
//        return Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(
//                OkHttpClient.Builder()
//                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                    .build()
//            )
//            .baseUrl(BuildConfig.BASE_URL)
//            .build()
//    }
//
//
//    @Singleton
//    @Provides
//    fun providePetCareApi(retrofit: Retrofit): ApiInterface {
//        return retrofit.create(ApiInterface::class.java)
//    }
//
//
//
//    @Singleton
//    @Provides
//    fun provideAuthApi(@Named("non-secure") retrofit: Retrofit): AuthApi {
//        return retrofit.create(AuthApi::class.java)
//    }
//
//
//}
//
//
//
//
//
//





























