package com.javichaques.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginDO(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String,
) : Parcelable
