package com.example.petapp.remote.apiService

import com.example.petapp.model.LoginRequest
import com.example.petapp.model.clientAuth.ClientLoginResponse
import com.example.petapp.model.clientAuth.ClientRegister
import com.example.petapp.model.clientAuth.ClientRegisterResponse
import com.example.petapp.model.walkerAuth.WalkerLoginResponse
import com.example.petapp.model.walkerAuth.WalkerRegister
import com.example.petapp.model.walkerAuth.WalkerRegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService{
    //base Url "https://apimascotas.jmacboy.com/api/"

    @POST("auth/clientlogin")
    suspend fun clientLogin(@Body credentials: LoginRequest): ClientLoginResponse

    @POST("auth/clientregister")
    suspend fun clientRegister(@Body clientCredentials: ClientRegister): ClientRegisterResponse

    @POST("auth/walkerlogin")
    suspend fun walkerLogin(@Body credentials: LoginRequest): WalkerLoginResponse

    @POST("auth/walkerregister")
    suspend fun walkerRegister(@Body walkerCredentials: WalkerRegister): WalkerRegisterResponse
}