package com.sample.loginregistercompose.domain.use_case

import com.sample.loginregistercompose.domain.model.User
import com.sample.loginregistercompose.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val userRepository: UserRepository) {

    operator fun invoke(email : String,password : String) : Flow<User> {
        return userRepository.login(email,password)
    }

}