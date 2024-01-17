package com.javichaques.core.network.model.dto

import com.javichaques.core.model.IdDO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdDTO(
    @SerialName("name") val name: String,
    @SerialName("value") val value: String,
) {
    fun toDomain() =
        IdDO(
            name = name,
            value = value,
        )
}
