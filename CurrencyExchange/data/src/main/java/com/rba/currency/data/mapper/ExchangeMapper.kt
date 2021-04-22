package com.rba.currency.data.mapper

import com.rba.currency.data.response.ExchangeResponse
import com.rba.currency.domain.model.ExchangeModel

object ExchangeMapper {

    fun transformResponseToModel(exchangeResponse: ExchangeResponse): ExchangeModel {
        return ExchangeModel(
            exchangeResponse.buy,
            exchangeResponse.sell,
            exchangeResponse.multiply,
            exchangeResponse.buyValue,
            exchangeResponse.sellValue,
            exchangeResponse.buyCurrency,
            exchangeResponse.sellCurrency
        )
    }

}