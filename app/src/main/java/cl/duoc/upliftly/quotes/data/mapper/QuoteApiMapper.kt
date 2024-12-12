package cl.duoc.upliftly.quotes.data.mapper

import cl.duoc.upliftly.quotes.data.model.ApiAdvice
import cl.duoc.upliftly.quotes.domain.Quote

fun ApiAdvice.toQuote(isFavorite: Boolean): Quote =
    Quote(id = this.slip.id, quote = this.slip.advice, isFavorite = isFavorite)
