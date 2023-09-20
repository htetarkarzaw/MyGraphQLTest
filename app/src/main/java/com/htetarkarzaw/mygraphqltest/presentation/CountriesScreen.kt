package com.htetarkarzaw.mygraphqltest.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.htetarkarzaw.mygraphqltest.domain.vo.CountryDetailVO
import com.htetarkarzaw.mygraphqltest.domain.vo.CountryVO

@Composable
fun CountriesScreen(
    state: CountriesViewModel.CountriesState,
    onSelectCountry: (code: String) -> Unit,
    onDismissDialog: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            )
            {
                items(state.countries) { country ->
                    CountryItem(
                        countryVO = country,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelectCountry(country.code)
                            }
                            .padding(16.dp)
                    )
                }
            }
        }
        if (state.selectedCountry != null) {
            CountryDialog(
                countryDetailVO = state.selectedCountry,
                onDismissDialog = onDismissDialog,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White)
                    .padding(16.dp)
            )
        }
    }

}

@Composable
private fun CountryDialog(
    countryDetailVO: CountryDetailVO,
    onDismissDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismissDialog) {
        val languages = remember(countryDetailVO.languages) {
            countryDetailVO.languages.joinToString()
        }
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = countryDetailVO.emoji,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = countryDetailVO.name,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Continent:${countryDetailVO.continent}"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Currency:${countryDetailVO.currency}"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Capital:${countryDetailVO.capital}"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Languages:${languages}"
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun CountryItem(
    countryVO: CountryVO,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = countryVO.emoji,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = countryVO.name,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = countryVO.capital,
                fontSize = 24.sp
            )
        }
    }
}