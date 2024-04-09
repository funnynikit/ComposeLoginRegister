package com.sample.loginregistercompose.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sample.loginregistercompose.domain.model.User
import com.sample.loginregistercompose.nav.Screen
import com.sample.loginregistercompose.presentation.state.RegisterState
import com.sample.loginregistercompose.presentation.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(
    navController: NavHostController,
    registerViewModel: RegisterViewModel? = hiltViewModel()
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember {  mutableStateOf("") }
    var confirmPasswordError by remember {  mutableStateOf("") }

    val registerState = registerViewModel?.registerState?.collectAsState()
    when(registerState?.value){
        is RegisterState.Idle -> {}
        is RegisterState.Loading -> {}
        is RegisterState.Success -> {
            navController.navigate(Screen.Login.route)
        }
        is RegisterState.Error -> {
            Toast.makeText(context,"Error in Registration",Toast.LENGTH_SHORT).show()
        }
        else -> {}
    }

    fun validateFields() : Boolean{

        var isValid = true

        if(!isValidEmail(email)){
            emailError = "Invalid Email"
            isValid = false
        }
        else
        {
            emailError = ""
        }

        if(!isValidPassword(password)){
            passwordError = "Password must be atleast 6 Char"
            isValid = false
        }
        else{
            passwordError = ""
        }

        if (!isPasswordMatch(password,confirmPassword)){
            confirmPasswordError = "Password and Confirm Password does not match"
            isValid = false
        }
        else{
            confirmPasswordError = ""
        }

        return isValid
    }

    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "SIGN UP", modifier = Modifier.padding(20.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = emailError.isNotEmpty()
        )

        if(emailError.isNotEmpty()){
            Text(text = emailError, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = mobile,
            onValueChange = { mobile = it },
            label = { Text("Mobile") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = passwordError.isNotEmpty()
        )

        if(passwordError.isNotEmpty())
        {
            Text(text = passwordError, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = confirmPasswordError.isNotEmpty()
        )

        if(confirmPasswordError.isNotEmpty())
        {
            Text(text = confirmPasswordError, color = Color.Red)
        }

        Button(
            onClick = {
                if (validateFields()){
                    scope.launch {
                        registerViewModel?.registerUser(
                            User(
                                username = username,
                                email = email,
                                mobile = mobile,
                                password = password
                            )
                        )
                    }
                }

            }, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(text = "Register")
        }
    }
}

fun isValidEmail(email : String) : Boolean{
    val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex()
    return emailPattern.matches(email)
}

fun isValidPassword(password : String) : Boolean{
    return password.length >= 6
}

fun isPasswordMatch(password: String,confirmPassword : String) : Boolean{

    var status: Boolean

    if (password!=confirmPassword){
        status = false
    }
    else{
        status = true
    }

    return status
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun previewSignUp() {
    SignUp(rememberNavController(), null)
}



