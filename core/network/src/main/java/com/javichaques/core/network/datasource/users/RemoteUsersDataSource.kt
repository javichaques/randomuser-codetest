package com.javichaques.core.network.datasource.users

import com.javichaques.core.network.api.UsersApi
import javax.inject.Inject

class RemoteUsersDataSource
    @Inject
    constructor(
        private val api: UsersApi,
    ) : UsersDataSource
