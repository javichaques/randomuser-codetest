package com.javichaques.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationDO(
    val street: StreetDO,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesDO,
    val timezone: TimezoneDO,
) : Parcelable
