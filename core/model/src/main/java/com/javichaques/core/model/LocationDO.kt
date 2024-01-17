package com.javichaques.core.model

data class LocationDO(
    val street: StreetDO,
    val city: String,
    val state: String,
    val country: String,
    val postcode: Int,
    val coordinates: CoordinatesDO,
    val timezone: TimezoneDO,
)
