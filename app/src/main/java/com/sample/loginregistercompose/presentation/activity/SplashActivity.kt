package com.sample.loginregistercompose.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.sample.loginregistercompose.R
import com.sample.loginregistercompose.ui.theme.LoginRegisterComposeTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginRegisterComposeTheme{
                navigateToNextScreenAfterDelay()
            }
        }
    }

    @Composable
    private fun navigateToNextScreenAfterDelay() {

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = "Splash Screen",
                modifier = Modifier.fillMaxSize()
            )
        }

        // Simulate a delay of 3 seconds before navigating to the next screen
        // You can replace this with actual navigation logic
        // For example: startActivity(Intent(this, MainActivity::class.java))
        // After 3 seconds, finish the current activity
        LaunchedEffect(true) {
            delay(3000L)
            // Start the next activity or navigate to the next screen here
            // For example: startActivity(Intent(this@MainActivity, NextActivity::class.java))
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}
