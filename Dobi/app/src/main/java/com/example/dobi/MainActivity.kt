package com.example.dobi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dobi.ui.theme.DobiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            DobiTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = Color(255, 255, 153)
                ){
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = com.example.dobi.Screen.Welcome.rout
                    ){
                        composable(com.example.dobi.Screen.Welcome.rout){
                            Welcome(navController = navController)
                        }
                        composable(Screen.Home.rout){
                            Home(navController = navController)
                        }
                        composable(Screen.Checker.rout){
                            Checker(navController = navController)
                        }
                        composable(Screen.Reminder.rout){
                            MedicationReminderScreen(navController = navController)
                        }
                        composable(Screen.Adviser.rout){
                            Adviser(navController = navController)
                        }
                        composable(Screen.Profile.rout){
                            ProfileSettingsScreen(navController = navController)
                        }
                        composable(Screen.Login.rout){
                            LoginScreen(navController = navController)
                        }
                        composable(Screen.Signup.rout){
                            SignUpScreen(navController = navController)
                        }
                    }

                }
            }
        }
    }
}

