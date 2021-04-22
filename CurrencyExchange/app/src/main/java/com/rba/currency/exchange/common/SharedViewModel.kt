package com.rba.currency.exchange.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rba.currency.exchangeview.model.ChangeModel

class SharedViewModel : ViewModel() {

    private val _update = MutableLiveData<ChangeModel>()
    val update: LiveData<ChangeModel>
        get() = _update

    fun update(model: ChangeModel) {
        _update.value = model
    }

}