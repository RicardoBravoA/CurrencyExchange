package com.rba.currency.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryModel(
    val name: String,
    val value: String,
    val currency: String,
) : Parcelable