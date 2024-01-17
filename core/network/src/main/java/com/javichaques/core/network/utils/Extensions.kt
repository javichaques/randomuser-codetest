package com.javichaques.core.network.utils

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import arrow.retrofit.adapter.either.networkhandling.HttpError
import arrow.retrofit.adapter.either.networkhandling.IOError
import arrow.retrofit.adapter.either.networkhandling.UnexpectedCallError
import com.javichaques.core.model.errors.RUError
import timber.log.Timber

fun CallError.timber() {
    when (this) {
        is IOError -> Timber.e(cause)
        is UnexpectedCallError -> Timber.e(cause)
        is HttpError -> {
            Timber.e(
                """
                Code: $code
                Message: $message
                Body: $body
                """.trimIndent(),
            )
        }
    }
}

fun <T> Either<CallError, T>.mapRUError(): Either<RUError, T> {
    return mapLeft {
        it.timber()

        when (it) {
            is HttpError -> {
                RUError.HttpError(
                    message = it.message,
                    code = it.code,
                    body = it.body,
                )
            }

            is IOError -> {
                RUError.IOError(
                    cause = it.cause,
                )
            }

            is UnexpectedCallError -> {
                RUError.UnexpectedCallError(
                    cause = it.cause,
                )
            }
        }
    }
}
