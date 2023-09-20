package com.htetarkarzaw.mygraphqltest.domain.usecase

import com.htetarkarzaw.mygraphqltest.domain.CountryClient
import com.htetarkarzaw.mygraphqltest.domain.vo.CountryDetailVO

class GetCountryUsecase(private val countryClient: CountryClient) {
    suspend operator fun invoke(code: String): CountryDetailVO? {
        return countryClient.getCountry(code)
    }
}