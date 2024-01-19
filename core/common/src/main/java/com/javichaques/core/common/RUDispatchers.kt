package com.javichaques.core.common

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dispatcher: RUDispatchers)

enum class RUDispatchers {
    IO,
}
