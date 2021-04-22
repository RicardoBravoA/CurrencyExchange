package com.rba.currency.exchange.strings

import android.content.Context
import com.rba.currency.exchange.R

class StringsProvider(private val context: Context) : StringsInterface {

    override fun errorMessage() = context.getString(R.string.error_message)

    override fun buySellValue(buy: String, sell: String) =
        context.getString(R.string.buy_sell_value, buy, sell)

}