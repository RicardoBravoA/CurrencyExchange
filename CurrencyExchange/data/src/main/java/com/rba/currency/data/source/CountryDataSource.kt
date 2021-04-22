package com.rba.currency.data.source

import com.rba.currency.data.mapper.CountryMapper
import com.rba.currency.data.mapper.ErrorMapper
import com.rba.currency.data.network.ApiManager
import com.rba.currency.data.repository.CountryRepository
import com.rba.currency.data.response.ErrorResponse
import com.rba.currency.data.util.RetrofitErrorUtil
import com.rba.currency.domain.model.CountryModel
import com.rba.currency.domain.model.ErrorModel
import com.rba.currency.domain.util.ResultType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryDataSource(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CountryRepository {

    override suspend fun get(): ResultType<List<CountryModel>, ErrorModel> {
        return withContext(dispatcher) {
            try {
                val response = ApiManager.get().country()
                if (response.isSuccessful) {
                    val data = response.body()
                    ResultType.Success(CountryMapper.transformResponseToModel(data))
                } else {
                    val error = RetrofitErrorUtil.parseError(response)!!
                    ResultType.Error(ErrorMapper.transformResponseToModel(error))
                }
            } catch (t: Throwable) {
                ResultType.Error(ErrorMapper.transformResponseToModel(ErrorResponse()))
            }
        }
    }

}