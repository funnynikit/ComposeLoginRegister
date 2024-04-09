package com.sample.loginregistercompose.domain.use_case

import com.sample.loginregistercompose.domain.model.User
import com.sample.loginregistercompose.domain.repository.UserRepository

class RegisterUseCase(private val userRepository: UserRepository) {

        suspend operator fun invoke(user: User){
            userRepository.registerUser(user)
        }
}