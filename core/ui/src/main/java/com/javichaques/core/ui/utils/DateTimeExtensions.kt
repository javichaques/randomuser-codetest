package com.javichaques.core.ui.utils

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

enum class DateTimeFormat(val value: String) {
    DayMonthYearShortDate("dd/MM/yyyy"),
}

fun ZonedDateTime.toString(format: DateTimeFormat): String {
    return withZoneSameInstant(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(format.value))
}
