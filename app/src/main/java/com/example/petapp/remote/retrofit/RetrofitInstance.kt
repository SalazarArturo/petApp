package com.example.petapp.remote.retrofit
import android.content.Context
import java.util.concurrent.TimeUnit
import com.example.petapp.local.TokenStore
import com.example.petapp.remote.apiService.AuthService
import com.example.petapp.remote.apiService.ClientUserLoggedService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance{
    private const val baseUrl = "https://apimascotas.jmacboy.com/api/"

    @Volatile
    private var retrofit: Retrofit? = null

    private fun getRetrofit(context: Context): Retrofit{
        return retrofit?: synchronized(this){
            retrofit?: buildRetrofit(context.applicationContext).also{
                retrofit = it
            }
        }
    }

    private fun buildRetrofit(context: Context): Retrofit{
        val tokenStore = TokenStore(context)

        val loggingInterceptor = HttpLoggingInterceptor().apply { //para debuggear
            level = HttpLoggingInterceptor.Level.BODY
        }

        val authInterceptor = AuthInterceptor(tokenStore)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // -------------------------------------------------------
    // Servicios expuestos (UNO POR INTERFAZ)
    // -------------------------------------------------------
    fun authService(context: Context): AuthService =
        getRetrofit(context).create(AuthService::class.java)

    fun clientUserLoggedService(context: Context): ClientUserLoggedService =
        getRetrofit(context).create(ClientUserLoggedService::class.java)
}


