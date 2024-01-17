package com.javichaques.core.network.model.dto

import com.javichaques.core.model.NameDO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NameDTO(
    @SerialName("title") val title: String,
    @SerialName("first") val first: String,
    @SerialName("last") val last: String,
) {
    fun toDomain() =
        NameDO(
            title = title,
            first = first,
            last = last,
        )
}
