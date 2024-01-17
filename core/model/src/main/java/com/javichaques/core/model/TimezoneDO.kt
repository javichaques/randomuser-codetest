package com.javichaques.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimezoneDO(
    val offset: String,
    val description: String,
) : Parcelable
