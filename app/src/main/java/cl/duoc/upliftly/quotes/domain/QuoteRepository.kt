package cl.duoc.upliftly.quotes.domain

import cl.duoc.upliftly.quotes.data.model.ApiAdvice

interface QuoteRepository {
    suspend fun getRandomAdvice(): Quote
}