package nondas.pap.petcareapp.infastracture.di


import nondas.pap.petcareapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nondas.pap.petcareapp.data.network.api.AuthApi
import nondas.pap.petcareapp.data.network.api.MedicineApi
import nondas.pap.petcareapp.data.network.api.PetApi
import nondas.pap.petcareapp.data.network.api.UserApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.*

@Module
@InstallIn(SingletonComponent::class)
object NetModule {



    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                .callTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build())
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
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }


    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }




}