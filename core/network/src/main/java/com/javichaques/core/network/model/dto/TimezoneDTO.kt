package com.javichaques.core.network.model.dto

import com.javichaques.core.model.TimezoneDO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimezoneDTO(
    @SerialName("offset") val offset: String,
    @SerialName("description") val description: String,
) {
    fun toDomain() =
        TimezoneDO(
            offset = offset,
            description = description,
        )
}
