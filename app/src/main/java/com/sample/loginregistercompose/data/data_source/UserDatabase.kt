package com.sample.loginregistercompose.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.loginregistercompose.domain.model.User


@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase(){

    abstract val userDao : UserDao

    companion object{
        const val DATABASE_NAME = "user_db"
    }
}