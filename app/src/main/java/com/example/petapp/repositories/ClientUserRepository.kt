package com.example.petapp.repositories

import android.content.Context
import com.example.petapp.model.clientUserlogged.ClientDataResponse
import com.example.petapp.remote.retrofit.RetrofitInstance
import android.net.Uri
import com.example.petapp.model.clientUserlogged.OwnerPhotoResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
class ClientUserRepository(context: Context) {

    private val appContext = context.applicationContext
    private val clientUserLogedService = RetrofitInstance.clientUserLoggedService(appContext)

    suspend fun getMyData(): ClientDataResponse?{
        try {
            val response = clientUserLogedService.getMe()
            return response
        }catch (e: Exception){
            throw Throwable("Error al hacer traer nuestros datos de la api en el repository: ${e.message}")
        }
    }
    suspend fun uploadProfilePhoto(uri: Uri): OwnerPhotoResponse{
        val contentResolver = appContext.contentResolver
        val inputStream = contentResolver.openInputStream(uri)
            ?: throw Exception("No se pudo abrir la imagen")
        val bytes = inputStream.readBytes()
        inputStream.close()

        val requestBody = bytes.toRequestBody("image/*".toMediaTypeOrNull())
        val multipart = MultipartBody.Part.createFormData(
            name = "photo",
            filename = "profile.jpg",
            body = requestBody
        )
        return clientUserLogedService.uploadProfilePhoto(multipart)
    }
}