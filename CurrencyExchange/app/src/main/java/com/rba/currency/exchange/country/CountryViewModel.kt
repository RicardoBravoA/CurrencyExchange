package com.rba.currency.exchange.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rba.currency.domain.model.CountryModel
import com.rba.currency.domain.model.ErrorModel
import com.rba.currency.domain.repository.CountryRepository
import com.rba.currency.domain.util.ResultType
import com.rba.currency.exchange.strings.StringsProvider
import com.rba.currency.exchange.util.SingleEvent
import kotlinx.coroutines.launch
import java.lang.Exception

class CountryViewModel(
    private val strings: StringsProvider,
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _data = MutableLiveData<SingleEvent<List<CountryModel>>>()
    val data: LiveData<SingleEvent<List<CountryModel>>>
        get() = _data

    private val _error = MutableLiveData<SingleEvent<String>>()
    val error: LiveData<SingleEvent<String>>
        get() = _error

    private val _apiError = MutableLiveData<SingleEvent<ErrorModel>>()
    val apiError: LiveData<SingleEvent<ErrorModel>>
        get() = _apiError

    fun get(origin: String) {

        viewModelScope.launch {
            try {
                when (val result = countryRepository.get(origin)) {
                    is ResultType.Success -> {
                        _data.value = SingleEvent(result.value)
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

}