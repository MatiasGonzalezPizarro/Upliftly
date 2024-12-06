package cl.duoc.upliftly.quotes.data.repository

import cl.duoc.upliftly.quotes.data.mapper.toQuote
import cl.duoc.upliftly.quotes.data.model.ApiAdvice
import cl.duoc.upliftly.quotes.data.network.QuoteApiService
import cl.duoc.upliftly.quotes.domain.Quote
import cl.duoc.upliftly.quotes.domain.QuoteRepository

class NetworkQuoteRepository(private val quoteApiService: QuoteApiService) : QuoteRepository {
    override suspend fun getRandomAdvice(): Quote =
        quoteApiService.getRandomAdvice().toQuote()
}