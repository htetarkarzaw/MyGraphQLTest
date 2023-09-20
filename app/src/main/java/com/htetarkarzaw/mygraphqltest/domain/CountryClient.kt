package com.htetarkarzaw.mygraphqltest.domain

import com.htetarkarzaw.mygraphqltest.domain.vo.CountryDetailVO
import com.htetarkarzaw.mygraphqltest.domain.vo.CountryVO

interface CountryClient {
    suspend fun getCountries():List<CountryVO>
    suspend fun getCountry(code:String):CountryDetailVO?
}