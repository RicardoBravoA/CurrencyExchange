package com.rba.currency.exchangeview.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExchangeViewModel(
    val buy: Double,
    val sell: Double,
    val multiply: Boolean,
    val originCurrencyName: String,
    val destinationCurrencyName: String,
    val originCurrencyValue: String,
    val destinationCurrencyValue: String
) : Parcelable