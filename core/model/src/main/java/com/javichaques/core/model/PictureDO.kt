package com.javichaques.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PictureDO(
    val large: String,
    val medium: String,
    val thumbnail: String,
) : Parcelable
