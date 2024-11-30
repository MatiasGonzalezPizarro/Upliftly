package cl.duoc.upliftly.quotes.data.di

import cl.duoc.upliftly.quotes.data.network.QuoteApiService
import cl.duoc.upliftly.quotes.data.repository.NetworkQuoteRepository
import cl.duoc.upliftly.quotes.data.repository.QuoteRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val quoteRepository: QuoteRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "https://api.adviceslip.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: QuoteApiService by lazy {
        retrofit.create(QuoteApiService::class.java)
    }

    override val quoteRepository: QuoteRepository by lazy {
        NetworkQuoteRepository(retrofitService)
    }
}