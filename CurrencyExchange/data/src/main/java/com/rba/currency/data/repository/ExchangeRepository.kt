package com.rba.currency.data.repository

import com.rba.currency.domain.model.ErrorModel
import com.rba.currency.domain.model.ExchangeModel
import com.rba.currency.domain.util.ResultType

interface ExchangeRepository {

    suspend fun get(): ResultType<ExchangeModel, ErrorModel>

}