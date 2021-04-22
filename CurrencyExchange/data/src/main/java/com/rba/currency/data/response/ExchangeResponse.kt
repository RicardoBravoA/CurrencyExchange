package com.rba.currency.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExchangeResponse(
    val buy: Double,
    val sell: Double,
    val multiply: Boolean,
    val buyValue: String,
    val sellValue: String,
    val buyCurrency: String,
    val sellCurrency: String
) : Parcelable