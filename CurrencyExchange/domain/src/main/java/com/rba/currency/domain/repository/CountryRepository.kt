package com.rba.currency.domain.repository

import com.rba.currency.domain.model.CountryModel
import com.rba.currency.domain.model.ErrorModel
import com.rba.currency.domain.util.ResultType

interface CountryRepository {

    suspend fun get(origin: String): ResultType<List<CountryModel>, ErrorModel>

}