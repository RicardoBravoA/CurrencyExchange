package com.rba.currency.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorModel(
    val code: Int,
    val message: String
) : Parcelable