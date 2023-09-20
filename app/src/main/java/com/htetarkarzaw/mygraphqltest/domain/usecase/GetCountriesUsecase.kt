package com.htetarkarzaw.mygraphqltest.domain.usecase

import com.htetarkarzaw.mygraphqltest.domain.CountryClient
import com.htetarkarzaw.mygraphqltest.domain.vo.CountryVO

class GetCountriesUsecase(private val countryClient: CountryClient) {
    suspend operator fun invoke(): List<CountryVO> {
        return countryClient.getCountries().sortedBy { it.name }
    }
}