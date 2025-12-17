package com.example.petapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petapp.local.TokenStore
import com.example.petapp.screens.NavScreens
import com.example.petapp.screens.clientScreens.LoginScreen
import com.example.petapp.screens.clientScreens.NewUserForm
import com.example.petapp.screens.clientScreens.WelcomeScreen
import com.example.petapp.ui.theme.PetAppTheme
import com.example.petapp.viewModels.clientViewModels.ClientLoginViewModel
import com.example.petapp.viewModels.clientViewModels.ClientLoginViewModelFactory
import com.example.petapp.viewModels.clientViewModels.NewUserFormViewModel
import com.example.petapp.viewModels.clientViewModels.NewUserFormViewModelFactory
import com.example.petapp.viewModels.clientViewModels.WelcomeScreenViewModel
import com.example.petapp.viewModels.clientViewModels.WelcomeScreenViewModelFactory
import kotlinx.coroutines.launch

class ClientAppActivity : ComponentActivity() {
    private val loginViewModel: ClientLoginViewModel by viewModels {
        ClientLoginViewModelFactory(applicationContext)
    }
    private val welcomeUserViewModel: WelcomeScreenViewModel by viewModels {
        WelcomeScreenViewModelFactory(applicationContext)
    }
    private val newUserFormViewModel: NewUserFormViewModel by viewModels {
        NewUserFormViewModelFactory(applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val tokenStore = TokenStore(applicationContext)

        lifecycleScope.launch {
            val result = tokenStore.hasSession()
            setContent {
                PetAppTheme {
                    NavigationApp(
                        loginViewModel,
                        newUserFormViewModel,
                        welcomeUserViewModel,
                        startDestination = if (result){
                            NavScreens.WELCOME.name
                        }else{
                            NavScreens.LOGIN.name
                        } )
                }
            }
        }

    }
}
@Composable
fun NavigationApp(
    loginViewModel: ClientLoginViewModel,
    newUserFormViewModel: NewUserFormViewModel,
    welcomeUserViewModel: WelcomeScreenViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(NavScreens.LOGIN.name){
            LoginScreen(loginViewModel, navController)
        }
        composable(NavScreens.WELCOME.name) {
            WelcomeScreen(welcomeUserViewModel, navController)
        }
        composable(NavScreens.NEW_USER_FORM.name) {
            NewUserForm(newUserFormViewModel, navController)
        }
    }
}