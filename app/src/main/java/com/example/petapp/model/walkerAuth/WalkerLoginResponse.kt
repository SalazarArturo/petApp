package com.example.petapp.model.walkerAuth

import com.google.gson.annotations.SerializedName

data class WalkerLoginResponse(
    @SerializedName("token") val accessToken: String
)
