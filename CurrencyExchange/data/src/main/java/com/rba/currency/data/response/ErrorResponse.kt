package com.rba.currency.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
    val code: Int = 100,
    val message: String = "Default error"
) : Parcelable