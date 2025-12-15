package com.example.petapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.petapp.local.TokenStore
import com.example.petapp.screens.StartScreen
import com.example.petapp.ui.theme.PetAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tokenStore = TokenStore(applicationContext)
        lifecycleScope.launch {
            val result = tokenStore.hasSession()
            val role = tokenStore.getUserRole()

            if(result && role != null){
                when(role){
                    "owner" ->{
                        startActivity(Intent(this@MainActivity, ClientAppActivity::class.java))
                    }
                    "walker" ->{
                        startActivity(Intent(this@MainActivity, WalkerAppActivity::class.java))
                    }
                }
                finish()
            }else{
                enableEdgeToEdge()
                setContent {
                    PetAppTheme {
                        StartScreen(
                            onClientSelected = {
                                startActivity(Intent(this@MainActivity, ClientAppActivity::class.java))
                            },
                            onWalkerSelected = {
                                startActivity(Intent(this@MainActivity, WalkerAppActivity::class.java))
                            })
                    }
                }
            }
        }

    }
}

