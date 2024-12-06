package cl.duoc.upliftly.di

import cl.duoc.upliftly.quotes.data.network.QuoteApiService
import cl.duoc.upliftly.quotes.data.repository.NetworkQuoteRepository
import cl.duoc.upliftly.quotes.domain.QuoteRepository
import cl.duoc.upliftly.quotes.presentation.discover_screen.DiscoverAdviceViewModel
import cl.duoc.upliftly.quotes.presentation.home_screen.HomeScreenViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

private const val baseUrl = "https://api.adviceslip.com/"

val module = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(baseUrl)
            .build()
    }

    single { get<Retrofit>().create(QuoteApiService::class.java) }
    singleOf(::NetworkQuoteRepository).bind<QuoteRepository>()
    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::DiscoverAdviceViewModel)
}