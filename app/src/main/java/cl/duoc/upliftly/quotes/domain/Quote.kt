package cl.duoc.upliftly.quotes.domain

data class Quote(
    val id: Int,
    val quote: String,
    val isFavorite: Boolean
)
