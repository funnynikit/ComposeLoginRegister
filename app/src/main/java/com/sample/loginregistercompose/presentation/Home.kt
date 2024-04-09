package com.sample.loginregistercompose.presentation

import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController : NavHostController)
{
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Home")
    }

    var backHandlingEnabled by remember { mutableStateOf(true) }
    BackHandler(backHandlingEnabled) {
        // Handle back press
        Log.d("Check","Back Pressed")
        navController.popBackStack()
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHome(){
    Home(rememberNavController())
}
