package com.example.petapp.repositories

import android.content.Context
import com.example.petapp.local.TokenStore
import com.example.petapp.model.LoginRequest
import com.example.petapp.model.clientAuth.ClientRegister
import com.example.petapp.model.clientAuth.ClientRegisterResponse
import com.example.petapp.remote.retrofit.RetrofitInstance

class AuthRepository(context: Context) {
    private val appContext = context.applicationContext

    private val authService = RetrofitInstance.authService(appContext)
    private val tokenStore = TokenStore(appContext)

    suspend fun loginClient(credentials: LoginRequest): Boolean{
        try {
            val response = authService.clientLogin(credentials)
            tokenStore.saveToken(response.accessToken)
            tokenStore.saveUserRole("owner")
            return true
        }catch (e: Exception){
            println("Error en el repositorio al poster los credenciales del cliente: ${e.message}")
        }
        return false
    }
    suspend fun registerNewUser(credentials: ClientRegister): ClientRegisterResponse?{
        try {
            val response = authService.clientRegister(credentials)
            return response
        }catch (e: Exception){
           throw Throwable("error al mandar nuestro request:${e.message}")
        }
    }
    suspend fun logOut(){
        tokenStore.clearSession()
    }
}