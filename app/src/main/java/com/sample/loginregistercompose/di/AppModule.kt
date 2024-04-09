package com.sample.loginregistercompose.di

import android.app.Application
import androidx.room.Room
import com.sample.loginregistercompose.data.data_source.UserDatabase
import com.sample.loginregistercompose.data.repository.UserRepositoryImpl
import com.sample.loginregistercompose.domain.repository.UserRepository
import com.sample.loginregistercompose.domain.use_case.LoginUseCase
import com.sample.loginregistercompose.domain.use_case.RegisterUseCase
import com.sample.loginregistercompose.domain.use_case.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(app : Application) : UserDatabase{
        return Room.databaseBuilder(app,UserDatabase::class.java,UserDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDatabase: UserDatabase) : UserRepository{
        return UserRepositoryImpl(userDatabase.userDao)
    }

    @Provides
    @Singleton
    fun provideUserUseCases(userRepository: UserRepository) : UserUseCases{
        return UserUseCases(loginUseCase = LoginUseCase(userRepository), registerUseCase = RegisterUseCase(userRepository))
    }
}