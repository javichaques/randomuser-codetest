package com.javichaques.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NameDO(
    val title: String,
    val first: String,
    val last: String,
) : Parcelable {
    fun getFullName(): String {
        return listOf(first, last).joinToString(" ")
    }
}
