package com.javichaques.core.network.di

import com.javichaques.core.network.datasource.users.RemoteUsersDataSource
import com.javichaques.core.network.datasource.users.UsersDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkBindsModule {
    @Binds
    fun bindsUsersDataSource(dataSource: RemoteUsersDataSource): UsersDataSource
}
