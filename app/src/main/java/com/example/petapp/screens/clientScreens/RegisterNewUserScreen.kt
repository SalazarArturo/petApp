package com.example.petapp.screens.clientScreens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.petapp.screens.NavScreens
import com.example.petapp.viewModels.clientViewModels.NewUserFormViewModel

@Composable
fun NewUserForm(nufvm: NewUserFormViewModel, navController: NavHostController){
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        OutlinedTextField(
            value = nufvm.email.value,
            onValueChange = {
                nufvm.email.value = it
                nufvm.emailError.value = ""
            },
            label = {
                Text(text = "E-mail")
            },
            isError = nufvm.emailError.value.isNotEmpty()
        )
        if(nufvm.emailError.value.isNotEmpty()){
           Text(text = nufvm.emailError.value,
               color = Color.Red)
        }

        OutlinedTextField(
            value = nufvm.nombreCompleto.value,
            onValueChange = {
                nufvm.nombreCompleto.value = it
                nufvm.nombreError.value = ""
            },
            label = {
                Text("Nombre")
            },
            isError = nufvm.nombreError.value.isNotEmpty()
        )
        if (nufvm.nombreError.value.isNotEmpty()){
            Text(nufvm.nombreError.value,
                color = Color.Red)
        }

        OutlinedTextField(
            value = nufvm.contraseha.value,
            onValueChange = {
                nufvm.contraseha.value = it
                nufvm.passwordError.value = ""
            },
            label = {
                Text("Contraseña")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            isError = nufvm.passwordError.value.isNotEmpty()
        )
        if(nufvm.passwordError.value.isNotEmpty()){
            Text(nufvm.passwordError.value,
                color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val result = nufvm.checkCredentials(
                    nufvm.email.value,
                    nufvm.nombreCompleto.value,
                    nufvm.contraseha.value)

                if (result){
                    nufvm.createNewUser(nufvm.email.value,
                        nufvm.nombreCompleto.value,
                        nufvm.contraseha.value,
                        success = {
                            navController.navigate(NavScreens.LOGIN.name)
                        },
                        error = {
                            Toast.makeText(context, nufvm.errorMessage.value,
                                Toast.LENGTH_SHORT)
                                .show()
                        })
                }
        }) {
            Text("Registrar")
        }

    }
}