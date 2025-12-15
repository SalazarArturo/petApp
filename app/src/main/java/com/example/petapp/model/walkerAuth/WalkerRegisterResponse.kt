package com.example.petapp.model.walkerAuth
data class WalkerRegisterResponse(
    val id: Int,
    val name: String,
    val email: String,
    val role: String,
    val photoUrl: String,
    val extras: WalkerExtrasResponse
)
