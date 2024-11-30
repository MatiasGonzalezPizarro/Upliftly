package cl.duoc.upliftly.quotes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiAdvice(
    val slip: Slip
)

@Serializable
data class Slip(
    val id: Int,
    val advice: String
)