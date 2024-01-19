package com.javichaques.core.data.di

import com.javichaques.core.data.repository.users.UsersRepository
import com.javichaques.core.data.repository.users.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataBindsModule {
    @Binds
    fun bindsUsersRepository(repository: UsersRepositoryImpl): UsersRepository
}
