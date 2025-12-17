package com.example.petapp.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(name = "session_prefs")

class TokenStore(private val context: Context){

    companion object{
        private val TOKEN_KEY = stringPreferencesKey("auth_token")
        private val USER_ROLE_KEY = stringPreferencesKey("user_role")
    }


    suspend fun saveToken(token: String){
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
        }
    }

    val tokenFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[TOKEN_KEY]
    }

    suspend fun getToken(): String?{
        return tokenFlow.firstOrNull()
    }

    suspend fun clearToken(){
        context.dataStore.edit { prefs ->
            prefs.remove(TOKEN_KEY)
        }
    }

    suspend fun saveUserRole(role: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_ROLE_KEY] = role
        }
    }

    val userRoleFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[USER_ROLE_KEY]
    }

    suspend fun getUserRole(): String? {
        return userRoleFlow.firstOrNull()
    }

    suspend fun clearUserRole() {
        context.dataStore.edit { prefs ->
            prefs.remove(USER_ROLE_KEY)
        }
    }

    val isLoggedInFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[TOKEN_KEY] != null
    }

    suspend fun clearSession(){
        context.dataStore.edit { prefs ->
            prefs.remove(TOKEN_KEY)
            prefs.remove(USER_ROLE_KEY)
        }
    }

    suspend fun hasSession(): Boolean{
        if (getToken() != null){
            return true
        }
        return false
    }
}