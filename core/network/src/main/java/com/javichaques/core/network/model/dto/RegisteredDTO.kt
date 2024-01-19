@file:UseSerializers(ZonedDateTimeSerializer::class)

package com.javichaques.core.network.model.dto

import com.javichaques.core.model.RegisteredDO
import com.javichaques.core.network.model.serializers.ZonedDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.ZonedDateTime

@Serializable
data class RegisteredDTO(
    @SerialName("date") val date: ZonedDateTime,
    @SerialName("age") val age: Int,
) {
    fun toDomain() =
        RegisteredDO(
            date = date,
            age = age,
        )
}
