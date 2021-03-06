package com.rba.currency.domain.repository

import com.rba.currency.domain.model.ErrorModel
import com.rba.currency.domain.model.ExchangeModel
import com.rba.currency.domain.util.ResultType

interface ExchangeRepository {

    suspend fun get(
        originCurrency: String,
        destinationCurrency: String
    ): ResultType<ExchangeModel, ErrorModel>

}