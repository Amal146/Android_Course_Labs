package com.example.dobi

sealed class Screen(val rout: String){
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Checker : Screen("checker")
    object Adviser : Screen("adviser")
    object Reminder : Screen("reminder")
    object Profile : Screen("profile")
    object Login : Screen("login")
    object Signup : Screen("signup")
}