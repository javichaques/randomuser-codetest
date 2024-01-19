package com.javichaques.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoordinatesDO(
    val latitude: Double,
    val longitude: Double,
) : Parcelable
