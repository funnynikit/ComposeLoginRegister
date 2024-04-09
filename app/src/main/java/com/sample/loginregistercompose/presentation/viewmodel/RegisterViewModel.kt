package com.sample.loginregistercompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.loginregistercompose.domain.model.User
import com.sample.loginregistercompose.domain.use_case.UserUseCases
import com.sample.loginregistercompose.presentation.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val userUseCases: UserUseCases) : ViewModel() {

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState : StateFlow<RegisterState> = _registerState

    fun registerUser(user : User){
        viewModelScope.launch {

            _registerState.value = RegisterState.Loading

            try {
                    userUseCases.registerUseCase.invoke(user)
                    _registerState.value = RegisterState.Success
            }
            catch (e : Exception)
            {
                _registerState.value = RegisterState.Error(e.message ?: "Registration Failed")
            }
        }
    }

}