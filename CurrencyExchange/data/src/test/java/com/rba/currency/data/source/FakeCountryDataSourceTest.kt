package com.rba.currency.data.source

import com.rba.currency.domain.model.CountryModel
import com.rba.currency.domain.model.ErrorModel
import com.rba.currency.domain.repository.CountryRepository
import com.rba.currency.domain.util.ResultType

class FakeCountryDataSourceTest : CountryRepository {

    var data = mutableListOf<CountryModel>()
    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun get(origin: String): ResultType<List<CountryModel>, ErrorModel> {
        if (shouldReturnError) {
            return ResultType.Error(ErrorModel(100, "Test exception"))
        }
        return ResultType.Success(data)
    }

    fun save(list: List<CountryModel>) {
        data.addAll(list)
    }
}