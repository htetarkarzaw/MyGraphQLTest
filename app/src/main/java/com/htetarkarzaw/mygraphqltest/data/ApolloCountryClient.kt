package com.htetarkarzaw.mygraphqltest.data

import com.apollographql.apollo3.ApolloClient
import com.htetarkarzaw.CountriesQuery
import com.htetarkarzaw.CountryQuery
import com.htetarkarzaw.mygraphqltest.domain.CountryClient
import com.htetarkarzaw.mygraphqltest.domain.vo.CountryDetailVO
import com.htetarkarzaw.mygraphqltest.domain.vo.CountryVO

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {
    override suspend fun getCountries(): List<CountryVO> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toCountryVO() }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): CountryDetailVO? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country?.toCountryDetailVO()
    }

}