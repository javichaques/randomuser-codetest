package com.javichaques.core.network.model.dto

import com.javichaques.core.model.PictureDO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PictureDTO(
    @SerialName("large") val large: String,
    @SerialName("medium") val medium: String,
    @SerialName("thumbnail") val thumbnail: String,
) {
    fun toDomain() =
        PictureDO(
            large = large,
            medium = medium,
            thumbnail = thumbnail,
        )
}
