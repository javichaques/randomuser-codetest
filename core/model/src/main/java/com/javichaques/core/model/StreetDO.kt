package com.javichaques.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StreetDO(
    val number: Int,
    val name: String,
) : Parcelable
