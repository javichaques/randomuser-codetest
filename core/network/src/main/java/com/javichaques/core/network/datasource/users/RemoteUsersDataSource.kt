package com.javichaques.core.network.datasource.users

import arrow.core.Either
import com.javichaques.core.model.errors.RUError
import com.javichaques.core.model.pagination.PaginationData
import com.javichaques.core.network.api.UsersApi
import com.javichaques.core.network.model.dto.UserDTO
import com.javichaques.core.network.model.responses.PagedResponse
import com.javichaques.core.network.utils.mapRUError
import javax.inject.Inject

class RemoteUsersDataSource
    @Inject
    constructor(private val api: UsersApi) : UsersDataSource {
        override suspend fun getUsers(pagination: PaginationData): Either<RUError, PagedResponse<UserDTO>> {
            return api.getUsers(
                seed = pagination.seed,
                results = pagination.results,
                page = pagination.page,
            ).mapRUError()
        }
    }
