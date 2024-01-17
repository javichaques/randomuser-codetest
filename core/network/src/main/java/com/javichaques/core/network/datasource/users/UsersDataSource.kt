package com.javichaques.core.network.datasource.users

import arrow.core.Either
import com.javichaques.core.model.errors.RUError
import com.javichaques.core.model.pagination.PaginationData
import com.javichaques.core.network.model.dto.UserDTO
import com.javichaques.core.network.model.responses.PagedResponse

interface UsersDataSource {
    suspend fun getUsers(pagination: PaginationData): Either<RUError, PagedResponse<UserDTO>>
}
