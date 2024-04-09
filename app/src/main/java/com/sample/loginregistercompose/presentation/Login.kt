package com.sample.loginregistercompose.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sample.loginregistercompose.R
import com.sample.loginregistercompose.nav.Screen
import com.sample.loginregistercompose.presentation.state.LoginState
import com.sample.loginregistercompose.presentation.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun Login(navController: NavHostController,loginViewModel: LoginViewModel? = hiltViewModel())
{
    val context = LocalContext.current

    val loginState = loginViewModel?.loginState?.collectAsState()
    when(loginState?.value)
    {
        is LoginState.Loading->{  Log.d("Check","Loading...")    }
        is LoginState.Success->{
            navController.navigate(Screen.Home.route)
            Log.d("Check","Success...")

        }
        is LoginState.Error-> {
            Toast.makeText(context,"Error on Login",Toast.LENGTH_SHORT).show()
            Log.d("Check","Error...")     }
        is LoginState.Idle ->{   Log.d("Check","Idle...")    }
        else-> {}
    }

    var email by remember {  mutableStateOf("") }
    var password by remember {  mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }


    Box(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.bg), contentDescription = "", modifier = Modifier.fillMaxSize())
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
        {

            Text(text = "LOGIN", modifier = Modifier.padding(20.dp))

            OutlinedTextField(value = email, onValueChange = { email = it }, modifier = Modifier.fillMaxWidth(), label = { Text(
                text = "Email"
            )}, singleLine = true)

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(value = password, onValueChange = { password = it }, modifier = Modifier.fillMaxWidth(), label = { Text(
                text = "Password"
            )}, singleLine = true)

            Spacer(modifier = Modifier.height(15.dp))

            Button(onClick = {
                loginViewModel?.loginUser(email,password)
            }, modifier = Modifier.fillMaxWidth().padding(start = 40.dp, end = 40.dp)) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(onClick = { navController.navigate(Screen.SignUp.route) }, modifier = Modifier.fillMaxWidth().padding(start = 40.dp, end = 40.dp)) {
                Text(text = "Register")
            }
        }
    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun previewLogin() {
    Login(rememberNavController(), null)
}