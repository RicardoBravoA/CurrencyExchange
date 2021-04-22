package com.rba.currency.data.mapper

import com.rba.currency.data.response.ErrorResponse
import com.rba.currency.domain.model.ErrorModel

object ErrorMapper {

    fun transformResponseToModel(errorResponse: ErrorResponse): ErrorModel {
        return ErrorModel(
            errorResponse.code,
            errorResponse.message
        )
    }

}