package com.javichaques.core.network.api

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.javichaques.core.network.model.dto.UserDTO
import com.javichaques.core.network.model.responses.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {
    @GET("?format=json")
    suspend fun getUsers(
        @Query("seed") seed: String?,
        @Query("gender") gender: String?,
        @Query("page") page: Int?,
        @Query("results") results: Int?,
    ): Either<CallError, PagedResponse<UserDTO>>
}
