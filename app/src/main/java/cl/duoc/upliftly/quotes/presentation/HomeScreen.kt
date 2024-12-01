package cl.duoc.upliftly.quotes.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.duoc.upliftly.R
import cl.duoc.upliftly.quotes.domain.Quote
import cl.duoc.upliftly.ui.theme.UpliftlyTheme
import coil.compose.AsyncImage

@Composable
fun QuoteCardItem(modifier: Modifier = Modifier, quote: Quote) {
    Card(modifier = modifier) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = "https://picsum.photos/600/400/?blur=2",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = quote.quote,
                modifier = Modifier.align(Alignment.BottomStart)
                    .padding(32.dp),
                style = MaterialTheme.typography.headlineLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 4

            )
        }
    }

}

@Preview
@Composable
private fun QuoteCardItemPreview() {
    UpliftlyTheme {
        QuoteCardItem(
            quote = Quote(
                1,
                "Take time once in a while to look up at the stars for at least 5 minutes, in order to comprehend your cosmic significance",
                ""
            )
        )
    }

}