package com.javichaques.core.model

data class NameDO(
    val title: String,
    val first: String,
    val last: String,
) {
    fun getFullName(): String {
        return listOf(first, last).joinToString(" ")
    }
}
