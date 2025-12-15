package com.example.petapp.model.walkerAuth

import com.google.gson.annotations.SerializedName

data class WalkerExtrasResponse(
    @SerializedName("price_hour") val priceHour: String,
    @SerializedName("rating_summary") val ratingSummary: Int
)
