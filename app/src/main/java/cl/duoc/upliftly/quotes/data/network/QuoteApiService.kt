package cl.duoc.upliftly.quotes.data.network

import cl.duoc.upliftly.quotes.data.model.ApiAdvice
import retrofit2.http.GET

interface QuoteApiService {
    @GET("advice")
    suspend fun getRandomAdvice(): ApiAdvice

}