package com.example.petapp.viewModels.clientViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petapp.repositories.AuthRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import com.example.petapp.repositories.ClientUserRepository
import android.net.Uri

class WelcomeScreenViewModel(
    private val authRepository: AuthRepository,
    private val clientUserRepository: ClientUserRepository
): ViewModel() {
    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _photoUrl = mutableStateOf<String?>(null)
    val photoUrl: State<String?> = _photoUrl

    fun getUserData(){
        viewModelScope.launch {
            try {
                val result = clientUserRepository.getMyData()
                _name.value = result?.name.toString()
                _photoUrl.value = if (result?.photoUrl == null || result.photoUrl == "null")
                    null else result.photoUrl
            }catch (e: Exception){
                println(e.message)
            }
        }
    }
    fun uploadProfilePhoto(uri: Uri){
        viewModelScope.launch {
            try {
                val result = clientUserRepository.uploadProfilePhoto(uri)

                val updatedUser = clientUserRepository.getMyData()
                _photoUrl.value = if (updatedUser?.photoUrl == null || updatedUser.photoUrl == "null")
                    null else updatedUser.photoUrl
                _name.value = updatedUser?.name.toString()

            }catch (e: Exception){
                println("Error al traer la foto: ${e.message}")
            }
        }
    }
    fun logOut(success: () -> Unit){
       viewModelScope.launch {
           authRepository.logOut()
           success()
       }
    }
}