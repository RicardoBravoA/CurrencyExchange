package com.rba.currency.exchange.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rba.currency.domain.model.ErrorModel
import com.rba.currency.domain.model.ExchangeModel
import com.rba.currency.domain.repository.ExchangeRepository
import com.rba.currency.domain.util.ResultType
import com.rba.currency.exchange.strings.StringsProvider
import com.rba.currency.exchange.util.SingleEvent
import com.rba.currency.exchangeview.model.ExchangeViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val strings: StringsProvider,
    private val exchangeRepository: ExchangeRepository
) : ViewModel() {

    private val _data = MutableLiveData<SingleEvent<ExchangeViewModel>>()
    val data: LiveData<SingleEvent<ExchangeViewModel>>
        get() = _data

    private val _error = MutableLiveData<SingleEvent<String>>()
    val error: LiveData<SingleEvent<String>>
        get() = _error

    private val _apiError = MutableLiveData<SingleEvent<ErrorModel>>()
    val apiError: LiveData<SingleEvent<ErrorModel>>
        get() = _apiError

    private val _buySellValue = MutableLiveData<SingleEvent<String>>()
    val buySellValue: LiveData<SingleEvent<String>>
        get() = _buySellValue

    fun getExchange(originCurrency: String, destinationCurrency: String) {

        viewModelScope.launch {
            try {
                when (val result = exchangeRepository.get(originCurrency, destinationCurrency)) {
                    is ResultType.Success -> {
                        val response = result.value
                        _data.value = SingleEvent(transformExchangeViewModel(response))
                        _buySellValue.value = SingleEvent(
                            strings.buySellValue(
                                response.buy.toString(),
                                response.sell.toString()
                            )
                        )
                    }
                    is ResultType.Error -> {
                        _apiError.value = SingleEvent(result.value)
                    }
                }
            } catch (e: Exception) {
                _error.value = SingleEvent(strings.errorMessage())
            }
        }

    }

    private fun transformExchangeViewModel(exchangeModel: ExchangeModel): ExchangeViewModel {
        return ExchangeViewModel(
            exchangeModel.buy,
            exchangeModel.sell,
            exchangeModel.multiply,
            exchangeModel.buyValue,
            exchangeModel.sellValue,
            exchangeModel.buyCurrency,
            exchangeModel.sellCurrency
        )
    }

}