package com.rba.currency.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryResponse(
    val countries: List<Country>
) : Parcelable {
    @Parcelize
    data class Country(
        val name: String,
        val info: String,
        val image: String,
        val value: String,
    ) : Parcelable
}