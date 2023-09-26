package nondas.pap.petcareapp.infastracture.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor,
        sslSocketFactory: SSLSocketFactory,
        trustManager: X509TrustManager
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .sslSocketFactory(sslSocketFactory, trustManager)
            .hostnameVerifier { _, _ -> true }
            .build()
    }

    @Singleton
    @Provides
    fun provideSslSocketFactory(trustManager: X509TrustManager): SSLSocketFactory {
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf(trustManager), SecureRandom())
        return sslContext.socketFactory
    }

    @Singleton
    @Provides
    fun provideX509TrustManager(): X509TrustManager {
        return object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        }
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

//    @Singleton
//    @Provides
//    @Named("configBaseUrl")
//    fun provideConfigBaseUrlRetrofit(
//        okHttpClient: OkHttpClient,
//        gsonConverterFactory: GsonConverterFactory
//    ): Retrofit {
//        return createRetrofit(BuildConfig.BASE_URL, okHttpClient, gsonConverterFactory)
//    }
//
//    @Singleton
//    @Provides
//    @Named("LocationServiceBaseUrl")
//    fun provideLocationServiceBaseUrlRetrofit(
//        okHttpClient: OkHttpClient,
//        gsonConverterFactory: GsonConverterFactory
//    ): Retrofit {
//        return createRetrofit("https://my.flykk.it/", okHttpClient, gsonConverterFactory)
//    }
//
//    @Singleton
//    @Provides
//    @Named("configNiumBaseUrl")
//    fun provideConfigNiumBaseUrlRetrofit(
//        okHttpClient: OkHttpClient,
//        gsonConverterFactory: GsonConverterFactory
//    ): Retrofit {
//        return createRetrofit(BuildConfig.NIUM_BASE_URL, okHttpClient, gsonConverterFactory)
//    }
//
//    private fun createRetrofit(
//        baseUrl: String,
//        okHttpClient: OkHttpClient,
//        gsonConverterFactory: GsonConverterFactory
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .client(okHttpClient)
//            .addConverterFactory(gsonConverterFactory)
//            .build()
//    }

//    @Singleton
//    @Provides
//    fun provideServiceAPI(@Named("configBaseUrl") retrofit: Retrofit): ServiceAPI {
//        return retrofit.create(ServiceAPI::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun provideNiumServiceAPI(@Named("configNiumBaseUrl") retrofit: Retrofit): NiumService {
//        return retrofit.create(NiumService::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun provideLocationServiceAPI(@Named("LocationServiceBaseUrl") retrofit: Retrofit): LocationServiceAPI {
//        return retrofit.create(LocationServiceAPI::class.java)
//    }
//
//    @Singleton
//    @Provides
//    @Named("cardIssuingBaseUrl")
//    fun provideCardIssuingBaseUrlRetrofit(
//        okHttpClient: OkHttpClient,
//        gsonConverterFactory: GsonConverterFactory
//    ): Retrofit {
//        return createRetrofit(BuildConfig.CARD_ISSUING_URL, okHttpClient, gsonConverterFactory)
//    }
//
//    @Singleton
//    @Provides
//    fun provideCardAPI(@Named("cardIssuingBaseUrl") retrofit: Retrofit): CardApi {
//        return retrofit.create(CardApi::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun provideCustomerAPI(@Named("cardIssuingBaseUrl") retrofit: Retrofit): CustomerApi {
//        return retrofit.create(CustomerApi::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun provideStatementsAPI(@Named("cardIssuingBaseUrl") retrofit: Retrofit): StatementsApi {
//        return retrofit.create(StatementsApi::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun provideTokenAPI(@Named("cardIssuingBaseUrl") retrofit: Retrofit): TokenApi {
//        return retrofit.create(TokenApi::class.java)
 //   }

}