package com.javichaques.core.data.repository.users

import arrow.core.Either
import com.javichaques.core.model.UserDO
import com.javichaques.core.model.errors.RUError
import com.javichaques.core.model.pagination.PagedList
import com.javichaques.core.model.pagination.PaginationData
import com.javichaques.core.network.datasource.users.UsersDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepositoryImpl
    @Inject
    constructor(
        private val dataSource: UsersDataSource,
    ) : UsersRepository {
        override suspend fun getUsers(
            gender: String?,
            pagination: PaginationData,
        ): Either<RUError, PagedList<UserDO>> {
            return dataSource.getUsers(
                gender = gender,
                pagination = pagination,
            ).map {
                it.toDomain().map { user ->
                    user.toDomain()
                }
            }
        }
    }
