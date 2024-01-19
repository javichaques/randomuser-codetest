package com.javichaques.core.model

enum class Gender(val value: String = "") {
    Unknown(),
    Male("male"),
    Female("female"),
    ;

    companion object {
        private val map = entries.associateBy(Gender::value)

        fun fromValue(value: String?) = map[value] ?: Unknown
    }
}
