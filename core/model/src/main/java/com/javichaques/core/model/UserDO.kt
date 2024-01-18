package com.javichaques.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDO(
    val gender: Gender,
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
) : Parcelable
