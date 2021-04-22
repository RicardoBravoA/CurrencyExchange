package com.rba.currency.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExchangeResponse(
    val buy: Double,
    val sell: Double,
    val multiply: Boolean
) : Parcelable