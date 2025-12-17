package com.example.petapp.viewModels.clientViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petapp.model.clientAuth.ClientRegister
import com.example.petapp.model.clientAuth.ClientRegisterResponse
import com.example.petapp.repositories.AuthRepository
import kotlinx.coroutines.launch

class NewUserFormViewModel(
    private val authRepository: AuthRepository
): ViewModel() {

    val email = mutableStateOf("")
    val nombreCompleto = mutableStateOf("")
    val contraseha = mutableStateOf("")
    val emailError = mutableStateOf("")
    val nombreError = mutableStateOf("")
    val passwordError = mutableStateOf("")
    val errorMessage = mutableStateOf("")

    fun createNewUser(
        email: String,
        nombre: String,
        contraseha: String,
        success: () -> Unit,
        error: () -> Unit) {
        viewModelScope.launch {
            try {
                val newUser = ClientRegister(
                    nombre,
                    email,
                    contraseha
                )
                val result = authRepository.registerNewUser(newUser)
                success()

            }catch (e: Exception){

                println(e.message)
                errorMessage.value = "Error al guardar sus datos, intente nuevamente."
                error()
            }
        }
    }
    fun checkCredentials(email: String, nombre: String, contraseha: String): Boolean{
        var flag = true
        if(email.isEmpty()){
            emailError.value = "No puede dejar el email vacio"
            flag = false
        }
        if (nombre.isEmpty()){
            nombreError.value = "No puede dejar el nombre vacio"
            flag = false
        }
        if(contraseha.isEmpty()){
            passwordError.value = "No puede dejar la contraseña vacia"
            flag = false
        }
        return flag
    }
}