package cl.duoc.upliftly.quotes.data.mapper

import cl.duoc.upliftly.quotes.data.database.QuoteEntity
import cl.duoc.upliftly.quotes.domain.Quote

fun QuoteEntity.toQuote(): Quote {
    return Quote(
        id = id,
        quote = quote,
        isFavorite = true
    )
}

fun Quote.toQuoteEntity(): QuoteEntity {
    return QuoteEntity(
        id = id,
        quote = quote,
    )
}