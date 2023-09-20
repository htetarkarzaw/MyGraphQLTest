package com.htetarkarzaw.mygraphqltest.data

import com.htetarkarzaw.CountriesQuery
import com.htetarkarzaw.CountryQuery
import com.htetarkarzaw.mygraphqltest.domain.vo.CountryDetailVO
import com.htetarkarzaw.mygraphqltest.domain.vo.CountryVO

fun CountryQuery.Country.toCountryDetailVO(): CountryDetailVO {
    return CountryDetailVO(
        code = this.code,
        name = this.name,
        emoji = this.emoji,
        capital = this.capital ?: "No capital",
        currency = this.currency ?: "No currency",
        languages = this.languages.map { it.name },
        continent = this.continent.name,
    )
}

fun CountriesQuery.Country.toCountryVO(): CountryVO {
    return CountryVO(
        code = this.code,
        name = this.name,
        emoji = this.emoji,
        capital = this.capital ?: "No capital"
    )
}