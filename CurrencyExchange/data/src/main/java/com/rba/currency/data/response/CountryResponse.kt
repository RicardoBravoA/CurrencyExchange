package com.rba.currency.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryResponse(
    val name: String,
    val value: String,
    val currency: String,
) : Parcelable