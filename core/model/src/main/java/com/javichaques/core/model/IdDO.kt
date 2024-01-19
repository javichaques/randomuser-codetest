package com.javichaques.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IdDO(
    val name: String,
    val value: String?,
) : Parcelable
