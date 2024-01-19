package com.javichaques.core.data.repository.users

import arrow.core.Either
import com.javichaques.core.model.UserDO
import com.javichaques.core.model.errors.RUError
import com.javichaques.core.model.pagination.PagedList
import com.javichaques.core.model.pagination.PaginationData

interface UsersRepository {
    suspend fun getUsers(
        gender: String?,
        pagination: PaginationData,
    ): Either<RUError, PagedList<UserDO>>
}
