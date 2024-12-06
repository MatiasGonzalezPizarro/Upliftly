package cl.duoc.upliftly.quotes.presentation.discover_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.upliftly.quotes.domain.Quote
import cl.duoc.upliftly.quotes.domain.QuoteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DiscoverAdviceViewModel(
    private val quoteRepository: QuoteRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(DiscoverScreenUiState())
    val uiState = _uiState.onStart {
        _uiState.update {
            it.copy(quotes = getQuotes())
        }
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = _uiState.value
    )

    private suspend fun getQuotes(): List<Quote> {
        return (0..4).map {
            quoteRepository.getRandomAdvice()
            // delay(2_000L)
        }
    }

}