package com.savas.scweatherapp.search.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.savas.scweatherapp.R
import com.savas.scweatherapp.search.SearchUIState
import com.savas.scweatherapp.search.SearchViewModel
import com.savas.scweatherapp.ui.common.ErrorUI
import com.savas.scweatherapp.ui.common.ProgressBar
import com.savas.scweatherapp.util.ErrorHelper
import kotlinx.coroutines.flow.take

@Composable
fun SearchScreen(
    modifier: Modifier,
    query: String,
    navigate: () -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val uiState by searchViewModel.searchUIState.collectAsState()

    LaunchedEffect(Unit) {
        if (query.isNotEmpty()) {
            searchViewModel.searchCity(query)
        }

        searchViewModel.navigate.take(1).collect {
            navigate.invoke()
        }

    }

    when (uiState) {

        is SearchUIState.Loading -> {
            ProgressBar()
        }

        is SearchUIState.Error -> {
            val context = LocalContext.current
            ErrorUI(
                ErrorHelper.getErrorMessage(context, (uiState as SearchUIState.Error).errorType)
            )
        }

        is SearchUIState.SearchData -> {
            val results = (uiState as SearchUIState.SearchData).searchResults

            LazyColumn(modifier = modifier.padding(top = dimensionResource(id = R.dimen.search_result_list_top_padding))) {
                items(results.size) { index ->
                    val item = results[index]
                    SearchResultCard(
                        item.name,
                        item.temp,
                        item.iconUrl
                    ) {
                        searchViewModel.selectCity(item.name, item.lat, item.lon)

                    }
                }
            }
        }

    }
}