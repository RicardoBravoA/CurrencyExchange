package com.rba.currency.exchangeview.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChangeModel(
    var origin: String,
    var destination: String,
    val updateOrigin: Boolean
) : Parcelable