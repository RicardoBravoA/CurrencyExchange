package com.rba.currency.data.source

import com.rba.currency.data.mapper.ErrorMapper
import com.rba.currency.data.mapper.ExchangeMapper
import com.rba.currency.data.network.ApiManager
import com.rba.currency.domain.repository.ExchangeRepository
import com.rba.currency.data.response.ErrorResponse
import com.rba.currency.data.util.RetrofitErrorUtil
import com.rba.currency.domain.model.ErrorModel
import com.rba.currency.domain.model.ExchangeModel
import com.rba.currency.domain.util.ResultType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExchangeDataSource(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ExchangeRepository {

    override suspend fun get(
        originCurrency: String,
        destinationCurrency: String
    ): ResultType<ExchangeModel, ErrorModel> {
        return withContext(dispatcher) {
            try {
                val response = ApiManager.get().exchange(originCurrency, destinationCurrency)
                if (response.isSuccessful) {
                    val data = response.body()!!
                    ResultType.Success(ExchangeMapper.transformResponseToModel(data))
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