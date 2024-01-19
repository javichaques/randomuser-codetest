package com.javichaques.core.model.errors

sealed class RUError(
    message: String = "",
    cause: Throwable? = null,
) : Error(message, cause) {
    class HttpError(
        message: String,
        val code: Int,
        val body: String,
    ) : RUError(message = message)

    class IOError(cause: Throwable) : RUError(cause = cause)

    class UnexpectedCallError(cause: Throwable = Error()) : RUError(cause = cause)
}

fun Throwable.toRUError(): RUError {
    return RUError.UnexpectedCallError(
        cause = this,
    )
}
