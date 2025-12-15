package com.example.petapp.remote.retrofit

import com.example.petapp.local.TokenStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

//para que todas las request lleven el token
class AuthInterceptor (
    private val tokenStore: TokenStore
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        //obtener request original
        val originalRequest = chain.request()

        //obtener el token almacenado en una corrutina runBlocking{}
        val token = runBlocking {
            tokenStore.getToken()
        }

        if(token.isNullOrEmpty()){
            return chain.proceed(originalRequest) //para que la api nos retorne el respectivo error al acceder a alguna ruta protegida que solicite un token o esta tambien sea admitida para endpoints publicos
        }

        //crear nueva request con un header de autorizacion
        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .addHeader("Content-type", "application/json")
            .build()

        return chain.proceed(newRequest)
    }
}
