package com.javichaques.core.network.model.dto

import com.javichaques.core.model.LocationDO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO(
    @SerialName("street") val street: StreetDTO,
    @SerialName("city") val city: String,
    @SerialName("state") val state: String,
    @SerialName("country") val country: String,
    @SerialName("postcode") val postcode: String,
    @SerialName("coordinates") val coordinates: CoordinatesDTO,
    @SerialName("timezone") val timezone: TimezoneDTO,
) {
    fun toDomain() =
        LocationDO(
            street = street.toDomain(),
            city = city,
            state = state,
            country = country,
            postcode = postcode,
            coordinates = coordinates.toDomain(),
            timezone = timezone.toDomain(),
        )
}
