package com.javichaques.core.network.model.dto

import com.javichaques.core.model.StreetDO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StreetDTO(
    @SerialName("number") val number: Int,
    @SerialName("name") val name: String,
) {
    fun toDomain() =
        StreetDO(
            number = number,
            name = name,
        )
}
