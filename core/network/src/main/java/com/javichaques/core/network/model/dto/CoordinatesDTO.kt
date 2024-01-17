package com.javichaques.core.network.model.dto

import com.javichaques.core.model.CoordinatesDO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoordinatesDTO(
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
) {
    fun toDomain() =
        CoordinatesDO(
            latitude = latitude,
            longitude = longitude,
        )
}
