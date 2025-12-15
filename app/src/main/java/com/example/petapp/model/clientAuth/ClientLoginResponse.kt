package com.example.petapp.model.clientAuth

import com.google.gson.annotations.SerializedName

data class ClientLoginResponse(
    @SerializedName("access_token") val accessToken: String
)