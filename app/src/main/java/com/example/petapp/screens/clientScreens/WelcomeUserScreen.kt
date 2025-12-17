package com.example.petapp.screens.clientScreens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.petapp.screens.NavScreens
import com.example.petapp.viewModels.clientViewModels.WelcomeScreenViewModel
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts


@Composable
fun WelcomeScreen(wsvm: WelcomeScreenViewModel, navController: NavHostController){
    val imagePickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                wsvm.uploadProfilePhoto(it)
            }
        }
    LaunchedEffect(Unit) {
        wsvm.getUserData()
    }
    Column(modifier = Modifier.fillMaxSize()
        .padding(25.dp)) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Bienvenido !")
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
        if (wsvm.name.value.isNotEmpty()){
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically){
                if (wsvm.photoUrl.value != null) {
                    AsyncImage(
                        model = wsvm.photoUrl.value,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                    )
                }else{
                    Button(onClick ={
                        imagePickerLauncher.launch("image/*")
                    }) {
                        Text("Agrega foto de perfil")
                    }
                }
                    Text(modifier = Modifier.padding(start = 8.dp),text = wsvm.name.value)
                }
            }
    }
}
