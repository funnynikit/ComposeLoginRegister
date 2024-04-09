package com.sample.loginregistercompose.nav

sealed class Screen(val route : String) {
    object SignUp : Screen("signup")
    object Login : Screen("login")
    object Home : Screen("home")
}