package com.htetarkarzaw.mygraphqltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.htetarkarzaw.mygraphqltest.presentation.CountriesScreen
import com.htetarkarzaw.mygraphqltest.presentation.CountriesViewModel
import com.htetarkarzaw.mygraphqltest.ui.theme.MyGraphQLTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyGraphQLTestTheme {
                val viewModel = hiltViewModel<CountriesViewModel>()
                val state by viewModel.countryState.collectAsState()
                CountriesScreen(
                    state = state,
                    onSelectCountry = {
                        viewModel.getCountry(it)
                    },
                    onDismissDialog = { viewModel.dismissCountryDialog() }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyGraphQLTestTheme {
        Greeting("Android")
    }
}