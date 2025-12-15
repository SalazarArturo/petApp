package com.example.petapp.viewModels.clientViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petapp.repositories.AuthRepository
import kotlinx.coroutines.launch

class WelcomeScreenViewModel(
    private val authRepository: AuthRepository
): ViewModel() {
    fun logOut(success: () -> Unit){
       viewModelScope.launch {
           authRepository.logOut()
           success()
       }
    }
}