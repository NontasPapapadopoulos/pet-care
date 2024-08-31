package nondas.pap.petcareapp.data.network.client.response

import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import nondas.pap.petcareapp.BuildConfig
import nondas.pap.petcareapp.data.network.api.ApiInterface
import nondas.pap.petcareapp.data.network.api.ApiResponseAdapterFactory
import nondas.pap.petcareapp.data.repository.DataStorageRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import javax.inject.Inject

interface ServiceProvider<T> {
    fun getToken(): String?
    fun setToken(token: String?)
    fun getService(): T
}


class RetrofitInstanceProvider @Inject constructor(
    private val datastoreRepository: DataStorageRepository
): ServiceProvider<ApiInterface> {

    private var apiService: ApiInterface? = null

    private val gson = GsonBuilder().create()


    override fun getToken(): String? {
        val token = runBlocking { datastoreRepository.getToken() }
        return token
    }

    override fun setToken(token: String?) {
        TODO("Not yet implemented")
    }

    override fun getService(): ApiInterface {
       return  apiService ?: setupClient()
    }

    private fun setupClient(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(ApiResponseAdapterFactory())
            .build()

           // .addCallAdapterFactory(Api)

        val service = retrofit.create(ApiInterface::class.java)
        apiService = service

        return service

    }

    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
            .addInterceptor(AuthInterceptor(getToken()))
            .build()
        return builder
    }


}



class AuthInterceptor @Inject constructor(
    private val token: String?
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        if (token != null)
             request.addHeader("Authorization", "Bearer $token")
        return chain.proceed(request.build())
    }


}
