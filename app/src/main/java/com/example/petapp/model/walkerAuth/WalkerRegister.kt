package com.example.petapp.model.walkerAuth

import com.google.gson.annotations.SerializedName

data class WalkerRegister(
    val name: String,
    val email: String,
    val password: String,
    @SerializedName("price_hour") val priceHour: String
)
