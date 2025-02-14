package com.sample.loginregistercompose.presentation.state

import com.sample.loginregistercompose.domain.model.User

sealed class LoginState {

    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user : User) : LoginState()
    data class Error(val message : String) : LoginState()

}