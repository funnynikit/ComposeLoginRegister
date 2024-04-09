package com.sample.loginregistercompose.domain.repository

import com.sample.loginregistercompose.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun login(email : String,password : String) : Flow<User>
    suspend fun registerUser(user : User)
}