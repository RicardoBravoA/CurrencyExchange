package com.rba.currency.exchange.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rba.currency.data.source.ExchangeDataSource
import com.rba.currency.exchange.strings.StringsProvider

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            val strings = StringsProvider(app)
            val exchangeDataSource = ExchangeDataSource()

            return MainViewModel(strings, exchangeDataSource) as T
        }
        throw IllegalArgumentException("Unable to construct view model")
    }
}