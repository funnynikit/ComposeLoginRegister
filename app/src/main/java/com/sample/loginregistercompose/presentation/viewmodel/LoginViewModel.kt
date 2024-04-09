package com.sample.loginregistercompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.loginregistercompose.domain.use_case.UserUseCases
import com.sample.loginregistercompose.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userUseCases: UserUseCases) : ViewModel() {

        private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
        val loginState : MutableStateFlow<LoginState> = _loginState

        fun loginUser(email : String, password : String){
            viewModelScope.launch {
                _loginState.value = LoginState.Loading
                try {
                    userUseCases.loginUseCase.invoke(email,password).collect{ user ->
                        _loginState.value = LoginState.Success(user)
                    }

                } catch (e : Exception){

                    _loginState.value = LoginState.Error(e.message ?: "Login Failed")
                }
            }
        }
}