package com.example.petapp.viewModels.clientViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petapp.repositories.AuthRepository

class NewUserFormViewModelFactory(
    private val context: Context
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = AuthRepository(context.applicationContext)
        return NewUserFormViewModel(repository) as T
    }
}