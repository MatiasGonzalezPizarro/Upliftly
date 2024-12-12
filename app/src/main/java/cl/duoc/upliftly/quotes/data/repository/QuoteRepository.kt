package cl.duoc.upliftly.quotes.data.repository

import android.util.Log
import cl.duoc.upliftly.quotes.data.database.QuoteDao
import cl.duoc.upliftly.quotes.data.mapper.toQuote
import cl.duoc.upliftly.quotes.data.mapper.toQuoteEntity
import cl.duoc.upliftly.quotes.data.network.QuoteApiService
import cl.duoc.upliftly.quotes.domain.Quote
import cl.duoc.upliftly.quotes.domain.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultQuoteRepository(
    private val quoteApiService: QuoteApiService,
    private val quoteDao: QuoteDao
) : QuoteRepository {

    override suspend fun getRandomAdvice(): Quote {
        return quoteApiService.getRandomAdvice().let { apiAdvice ->
            apiAdvice.toQuote(
                isFavorite = isFavoriteQuote(apiAdvice.slip.id)
            )

        }
    }

    override fun getFavoriteQuotes(): Flow<List<Quote>> {
        return quoteDao.getAllQuotes().map { quotes ->
            quotes.map { it.toQuote().also { quo ->
                Log.i("QuoteRepository", "Quote: ${quo.quote}")
            } }
        }
    }

    override suspend fun isFavoriteQuote(quoteId: Int): Boolean {
        return quoteDao.isFavoriteQuote(quoteId) != null
    }

    override suspend fun toggleFavorite(quote: Quote) {
        if (isFavoriteQuote(quote.id)) {
            quoteDao.deleteQuote(quote.id)
        } else {
            quoteDao.insertQuote(quote.toQuoteEntity())
        }
    }
}