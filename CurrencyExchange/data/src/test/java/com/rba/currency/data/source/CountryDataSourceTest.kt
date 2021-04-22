package com.rba.currency.data.source

import com.rba.currency.data.util.MainCoroutineRule
import com.rba.currency.domain.model.CountryModel
import com.rba.currency.domain.model.ErrorModel
import com.rba.currency.domain.util.ResultType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CountryDataSourceTest {

    private var repository = FakeCountryDataSourceTest()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val countryModel = CountryModel(
        "Peru", "info", "image",
        "PEN"
    )

    private val error = ErrorModel(100, "Test exception")
    private lateinit var result: List<CountryModel>

    @Before
    fun setup() {

        result = listOf(countryModel, countryModel, countryModel)
    }

    @Test
    fun get_Success() = mainCoroutineRule.runBlockingTest {
        repository.save(result)
        repository.setReturnError(false)

        val data = repository.get("PEN") as ResultType.Success
        MatcherAssert.assertThat(data.value, IsEqual(result))
    }

    @Test
    fun get_Error() = mainCoroutineRule.runBlockingTest {
        repository.setReturnError(true)

        val data = repository.get("PEN") as ResultType.Error
        Assert.assertNotNull(data.value)
        MatcherAssert.assertThat(data.value, IsEqual(error))
    }

}