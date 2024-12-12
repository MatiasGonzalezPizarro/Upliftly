package cl.duoc.upliftly.quotes.domain

import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    suspend fun getRandomAdvice(): Quote
    fun getFavoriteQuotes(): Flow<List<Quote>>
    suspend fun isFavoriteQuote(quoteId: Int): Boolean
    suspend fun toggleFavorite(quote: Quote)
}