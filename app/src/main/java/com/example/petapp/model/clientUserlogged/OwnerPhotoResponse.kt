package com.example.petapp.model.clientUserlogged

data class OwnerPhotoResponse(
    val id: Int,
    val name: String,
    val email: String,
    val role: String,
    val photoUrl: String
)
