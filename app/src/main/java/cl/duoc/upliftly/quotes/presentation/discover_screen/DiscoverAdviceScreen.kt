package cl.duoc.upliftly.quotes.presentation.discover_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cl.duoc.upliftly.quotes.domain.Quote
import cl.duoc.upliftly.quotes.presentation.home_screen.QuoteCardItem
import cl.duoc.upliftly.ui.theme.UpliftlyTheme

@Composable
fun DiscoverAdviceScreen(modifier: Modifier = Modifier) {
    val quote=Quote(
        1,
        "Take time once in a while to look up at the stars for at least 5 minutes, in order to comprehend your cosmic significance",
        ""
    )
    QuoteCardItem(quote = quote, modifier = modifier)

}

@Preview
@Composable
private fun DiscoverAdviceScreenPreview() {
    UpliftlyTheme {
        DiscoverAdviceScreen(

            )
    }
}