package com.sample.loginregistercompose.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.loginregistercompose.presentation.Home
import com.sample.loginregistercompose.presentation.Login
import com.sample.loginregistercompose.presentation.SignUp

@Composable
fun navRoute()
{
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            Login(navController = navController)
        }
        composable(route = Screen.SignUp.route) {
            SignUp(navController = navController)
        }
        composable(route = Screen.Home.route) {
            Home(navController = navController)
        }
    }
}
