package com.example.petapp.screens.clientScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.petapp.screens.NavScreens
import com.example.petapp.viewModels.clientViewModels.WelcomeScreenViewModel


@Composable
fun WelcomeScreen(wsvm: WelcomeScreenViewModel, navController: NavHostController){
    Column(modifier = Modifier.fillMaxSize()
        .padding(13.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Bienvenido papu")
            Button(onClick = {
                wsvm.logOut {
                    navController.navigate(NavScreens.LOGIN.name){
                        popUpTo(0){inclusive = true}
                    }
                }
            }) {
                Text(text = "Cerrar session",)
            }
        }

    }
}