package com.javichaques.core.network.model.dto

import com.javichaques.core.model.LoginDO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginDTO(
    @SerialName("uuid") val uuid: String,
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
    @SerialName("salt") val salt: String,
    @SerialName("md5") val md5: String,
    @SerialName("sha1") val sha1: String,
    @SerialName("sha256") val sha256: String,
) {
    fun toDomain() =
        LoginDO(
            uuid = uuid,
            username = username,
            password = password,
            salt = salt,
            md5 = md5,
            sha1 = sha1,
            sha256 = sha256,
        )
}
