package com.rba.currency.exchange.country

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rba.currency.data.source.CountryDataSource
import com.rba.currency.exchange.strings.StringsProvider

@Suppress("UNCHECKED_CAST")
class CountryViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {

            val strings = StringsProvider(app)
            val countryDataSource = CountryDataSource()

            return CountryViewModel(strings, countryDataSource) as T
        }
        throw IllegalArgumentException("Unable to construct view model")
    }
}