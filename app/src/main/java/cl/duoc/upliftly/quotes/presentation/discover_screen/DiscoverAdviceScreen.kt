package cl.duoc.upliftly.quotes.presentation.discover_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cl.duoc.upliftly.quotes.domain.Quote
import cl.duoc.upliftly.quotes.presentation.home_screen.QuoteCardItem
import cl.duoc.upliftly.ui.theme.UpliftlyTheme


@Composable
fun DiscoverAdviceRoute(modifier: Modifier = Modifier, viewModel: DiscoverAdviceViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    DiscoverAdviceScreen(uiState = uiState.value)
}

@Composable
fun DiscoverAdviceScreen(modifier: Modifier = Modifier, uiState: DiscoverScreenUiState) {
    val pagerState = rememberPagerState { 5 }
    VerticalPager(state = pagerState) { page ->
        val quote = uiState.quotes.getOrNull(page)

        if (quote == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }else{
            QuoteCardItem(
                quote = quote, modifier = Modifier.fillMaxSize(), showFullQuote = true, cachedImage=true
            )
        }

    }

}

@Preview
@Composable
private fun DiscoverAdviceScreenPreview() {
    UpliftlyTheme {
        DiscoverAdviceScreen(
            uiState = DiscoverScreenUiState(
                quotes = listOf(
                    Quote(
                        1,
                        "Take time once in a while to look up at the stars for at least 5 minutes, in order to comprehend your cosmic significance",

                        )
                )
            )
        )
    }
}