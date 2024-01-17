package com.javichaques.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.ZonedDateTime

@Parcelize
data class RegisteredDO(
    val date: ZonedDateTime,
    val age: Int,
) : Parcelable
