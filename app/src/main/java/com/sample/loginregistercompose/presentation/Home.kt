package com.sample.loginregistercompose.presentation

import DataStoreManager
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun Home(navController : NavHostController)
{
    val context = LocalContext.current
    val scope =  rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Home")
    }

    var backHandlingEnabled by remember { mutableStateOf(true) }
    BackHandler(backHandlingEnabled) {
        // Handle back press
        Log.d("Check","Back Pressed")
        navController.popBackStack()
    }

    LaunchedEffect(Unit) {
        scope.launch {
            val name = DataStoreManager(context).getUserName()
            val email = DataStoreManager(context).getUserEmail()
            val mobile = DataStoreManager(context).getUserMobile()
            Log.d("Check","Name :$name")
            Log.d("Check","Email :$email")
            Log.d("Check","Mobile :$mobile")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHome(){
    Home(rememberNavController())
}
