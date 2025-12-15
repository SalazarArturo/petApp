package com.example.petapp.model.clientAuth

import com.google.gson.annotations.SerializedName

data class ClientRegisterResponse(

    val name: String,
    val email: String,
    val role: String,
    val id: Int,
    val photoUrl: String
)
