package com.javichaques.core.network.model.dto

import com.javichaques.core.model.InfoDO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoDTO(
    @SerialName("seed") val seed: String,
    @SerialName("results") val results: Int,
    @SerialName("page") val page: Int,
    @SerialName("version") val version: String,
) {
    fun toDomain() =
        InfoDO(
            seed = seed,
            results = results,
            page = page,
            version = version,
        )
}
