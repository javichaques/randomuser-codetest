package com.javichaques.core.network.model.dto

import com.javichaques.core.model.UserDO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    @SerialName("gender") val gender: String,
    @SerialName("name") val name: NameDTO,
    @SerialName("location") val location: LocationDTO,
    @SerialName("email") val email: String,
    @SerialName("login") val login: LoginDTO,
    @SerialName("dob") val dob: DobDTO,
    @SerialName("registered") val registered: RegisteredDTO,
    @SerialName("phone") val phone: String,
    @SerialName("cell") val cell: String,
    @SerialName("id") val id: IdDTO,
    @SerialName("picture") val picture: PictureDTO,
    @SerialName("nat") val nat: String,
) {
    fun toDomain() =
        UserDO(
            gender = gender,
            name = name.toDomain(),
            location = location.toDomain(),
            email = email,
            login = login.toDomain(),
            dob = dob.toDomain(),
            registered = registered.toDomain(),
            phone = phone,
            cell = cell,
            id = id.toDomain(),
            picture = picture.toDomain(),
            nat = nat,
        )
}
