package com.example.petapp.screens.clientScreens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.petapp.screens.NavScreens
import com.example.petapp.viewModels.clientViewModels.ClientLoginViewModel

@Composable
fun LoginScreen(lvm: ClientLoginViewModel, navController: NavHostController){
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
        .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Que gusto volver a verte !")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = lvm.email.value,
            onValueChange ={
                lvm.email.value = it
                lvm.emailErrorMessage.value = ""
            },
            label = {
                Text(text = "E-mail")
            },
            isError = lvm.emailErrorMessage.value.isNotEmpty()
        )
        if (lvm.emailErrorMessage.value.isNotEmpty()){
            Text(text = lvm.emailErrorMessage.value,
                color = Color.Red
            )
        }
        OutlinedTextField(
            value = lvm.password.value,
            onValueChange = {
                lvm.password.value = it
                lvm.passwordErrorMessage.value = ""
            },
            label = {
                Text("Password")
            },
            isError = lvm.passwordErrorMessage.value.isNotEmpty()
        )
        if(lvm.passwordErrorMessage.value.isNotEmpty()){
            Text(text = lvm.passwordErrorMessage.value,
                color = Color.Red)
        }
        Button(
            onClick = {
                val result = lvm.checkCredentials(lvm.email.value, lvm.password.value)
                if(result){
                    lvm.sendCredentials(lvm.email.value, lvm.password.value, success = {
                        navController.navigate(NavScreens.WELCOME.name)
                    }, error = {

                        Toast.makeText(context,
                            "Error en los credenciales",
                            Toast.LENGTH_SHORT)
                            .show()
                    })
                }
            }
        ) {
            Text(text = "Ingresar")
        }
    }
}