package com.javichaques.core.model

data class UserDO(
    val gender: String,
    val name: NameDO,
    val location: LocationDO,
    val email: String,
    val login: LoginDO,
    val dob: DobDO,
    val registered: RegisteredDO,
    val phone: String,
    val cell: String,
    val id: IdDO,
    val picture: PictureDO,
    val nat: String,
)
