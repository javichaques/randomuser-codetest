@file:UseSerializers(ZonedDateTimeSerializer::class)

package com.javichaques.core.network.model.dto

import com.javichaques.core.model.DobDO
import com.javichaques.core.network.model.serializers.ZonedDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.ZonedDateTime

@Serializable
data class DobDTO(
    @SerialName("date") val date: ZonedDateTime,
    @SerialName("age") val age: Int,
) {
    fun toDomain() =
        DobDO(
            date = date,
            age = age,
        )
}
