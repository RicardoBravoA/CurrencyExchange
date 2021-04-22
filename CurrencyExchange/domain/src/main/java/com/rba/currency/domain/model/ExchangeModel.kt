package com.rba.currency.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExchangeModel(
    val buy: Double,
    val sell: Double,
    val multiply: Boolean
) : Parcelable