package com.rba.currency.data.mapper

import com.rba.currency.data.response.CountryResponse
import com.rba.currency.domain.model.CountryModel

object CountryMapper {

    fun transformResponseToModel(countryResponse: CountryResponse?): List<CountryModel> {
        val list = mutableListOf<CountryModel>()
        countryResponse?.countries?.forEach {
            list.add(CountryModel(it.name, it.info, it.image, it.value))
        }
        return list
    }

}