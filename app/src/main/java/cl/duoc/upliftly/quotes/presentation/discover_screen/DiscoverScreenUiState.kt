package cl.duoc.upliftly.quotes.presentation.discover_screen

import cl.duoc.upliftly.quotes.domain.Quote

data class DiscoverScreenUiState(
    val quotes: List<Quote> = emptyList()
)
