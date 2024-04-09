package com.sample.loginregistercompose.data.repository

import com.sample.loginregistercompose.data.data_source.UserDao
import com.sample.loginregistercompose.domain.model.User
import com.sample.loginregistercompose.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override fun login(email: String, password: String): Flow<User> {
        return userDao.getUserByEmailAndPassword(email, password)
    }

    override suspend fun registerUser(user: User) {
        userDao.insertUser(user)
    }

}