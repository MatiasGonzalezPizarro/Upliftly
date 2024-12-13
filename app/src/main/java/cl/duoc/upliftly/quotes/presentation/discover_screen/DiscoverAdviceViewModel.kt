package cl.duoc.upliftly.quotes.presentation.discover_screen

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cl.duoc.upliftly.quotes.domain.Quote
import cl.duoc.upliftly.quotes.domain.QuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DiscoverAdviceViewModel(
    private val quoteRepository: QuoteRepository
) : ViewModel() {
    val favoriteQuotes = quoteRepository.getFavoriteQuotes().asLiveData()

    private var _uiState = MutableStateFlow(DiscoverScreenUiState())
    val uiState = _uiState.onStart {
        _uiState.update {
            it.copy(quotes = getQuotes(), favorites = quoteRepository.getFavoriteQuotes().first())
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

    var currentAdvice = MutableStateFlow<Quote?>(null)
        private set

    fun updateCurrentAdvice(quote: Quote?) {
        currentAdvice.value = quote
    }

    fun onAdviceFavorited(quote: Quote) {
        viewModelScope.launch {
            quoteRepository.toggleFavorite(quote)
            val updatedFavorites = quoteRepository.getFavoriteQuotes().first()
            _uiState.update {
                it.copy(favorites = updatedFavorites)
            }
        }
    }


    fun onAdviceShared(quote: Quote): Intent {
        return Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, "From Upliftly: \"${quote.quote}\"")
            type = "text/plain"
        }.let {
            Intent.createChooser(it, "Share Quote")
        }
    }

}