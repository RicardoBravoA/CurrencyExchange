package com.rba.currency.data.repository

import com.rba.currency.domain.model.CountryModel
import com.rba.currency.domain.model.ErrorModel
import com.rba.currency.domain.util.ResultType

interface CountryRepository {

    suspend fun get(): ResultType<List<CountryModel>, ErrorModel>

}