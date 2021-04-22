package com.rba.currency.exchangeview.util

import java.text.DecimalFormat

fun Float.toMoney(): String {
    val df = DecimalFormat("###,###,###.##")
    return df.format(this)
}