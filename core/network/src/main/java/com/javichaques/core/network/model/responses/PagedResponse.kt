package com.javichaques.core.network.model.responses

import com.javichaques.core.model.pagination.PagedList
import com.javichaques.core.network.model.dto.InfoDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagedResponse<T>(
    @SerialName("results") val results: List<T>,
    @SerialName("info") val info: InfoDTO,
) {
    fun toDomain() =
        PagedList<T>(
            seed = info.seed,
            results = info.results,
            page = info.page,
            items = results,
        )
}
