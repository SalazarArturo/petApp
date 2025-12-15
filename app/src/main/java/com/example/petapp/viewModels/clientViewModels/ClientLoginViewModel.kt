package com.example.petapp.viewModels.clientViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petapp.model.LoginRequest
import com.example.petapp.repositories.AuthRepository
import kotlinx.coroutines.launch

class ClientLoginViewModel(
    private val authRepository: AuthRepository
): ViewModel(){

    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val emailErrorMessage = mutableStateOf("")
    val passwordErrorMessage = mutableStateOf("")

    fun sendCredentials(email: String, password: String, success: () -> Unit, error: () -> Unit){
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(email, password)
                val result = authRepository.loginClient(loginRequest)
                if (result){
                    success()
                }else{
                    error()
                }
            }catch(e: Exception){
                println("Error capturado en el viewModel: ${e.message}")
            }
        }
    }
    fun checkCredentials(email: String, password: String): Boolean{

        var flag = true
        if(email.isEmpty()){
            emailErrorMessage.value = "Debe ingresar un email"
            flag = false
        }
        if(password.isEmpty()){
            passwordErrorMessage.value = "Debe ingresar una contraseña"
            flag = false
        }
        return flag
    }
}