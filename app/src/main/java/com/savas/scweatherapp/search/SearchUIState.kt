package com.savas.scweatherapp.search

import com.savas.scweatherapp.ErrorType

sealed class SearchUIState {
    data class Data(val searchResults: List<SearchResultData>) : SearchUIState()
    data class Error(val errorType: ErrorType) : SearchUIState()
    data object Loading : SearchUIState()
}