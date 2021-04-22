package com.rba.currency.exchange.strings

interface StringsInterface {

    fun errorMessage(): String

    fun buySellValue(buy: String, sell: String): String
}