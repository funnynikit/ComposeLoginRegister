package com.sample.loginregistercompose.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.loginregistercompose.domain.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : User)

    @Query("SELECT * from user WHERE email = :email AND password = :password")
    fun getUserByEmailAndPassword(email : String,password : String) : Flow<User>
}