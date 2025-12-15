package com.example.petapp.remote.apiService

import com.example.petapp.model.clientUserlogged.OwnerPhotoResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ClientUserLoggedService {

    @Multipart
    @POST("owners/photo")
    suspend fun uploadProfilePhoto(
        @Part photo: MultipartBody.Part
    ): OwnerPhotoResponse
}