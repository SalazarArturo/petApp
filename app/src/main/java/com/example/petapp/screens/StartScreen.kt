package com.example.petapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen(onClientSelected: () -> Unit,
                onWalkerSelected: () -> Unit) {
    //tal vez aqui ... implementar logica para verificar si hay un token previo
    Column(modifier = Modifier.fillMaxSize()
        .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Text(text = "¡Bienvenido! Inicia sesion en tu modo")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onClientSelected){
            Text(text = "Iniciar como Cliente")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onWalkerSelected){
            Text(text = "Iniciar como paseador")
        }
    }
}