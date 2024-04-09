package com.sample.loginregistercompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id : Int? = null,
    val username : String,
    val email : String,
    val mobile : String,
    val password : String
)