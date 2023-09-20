package com.htetarkarzaw.mygraphqltest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htetarkarzaw.mygraphqltest.domain.usecase.GetCountriesUsecase
import com.htetarkarzaw.mygraphqltest.domain.usecase.GetCountryUsecase
import com.htetarkarzaw.mygraphqltest.domain.vo.CountryDetailVO
import com.htetarkarzaw.mygraphqltest.domain.vo.CountryVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUsecase: GetCountriesUsecase,
    private val getCountryUsecase: GetCountryUsecase
) : ViewModel() {

    private val _countryState = MutableStateFlow(CountriesState())
    val countryState = _countryState.asStateFlow()

    data class CountriesState(
        val countries: List<CountryVO> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: CountryDetailVO? = null
    )

    init {
        viewModelScope.launch {
            _countryState.update { it.copy(isLoading = true) }
            _countryState.update {
                it.copy(
                    countries = getCountriesUsecase.invoke(),
                    isLoading = false
                )
            }
        }
    }

    fun getCountry(code: String) {
        viewModelScope.launch {
            _countryState.update {
                it.copy(
                    selectedCountry = getCountryUsecase(code)
                )
            }
        }
    }

    fun dismissCountryDialog() {
        _countryState.update {
            it.copy(
                selectedCountry = null
            )
        }
    }
}