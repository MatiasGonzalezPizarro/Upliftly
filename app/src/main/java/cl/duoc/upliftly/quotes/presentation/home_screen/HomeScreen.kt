package cl.duoc.upliftly.quotes.presentation.home_screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.duoc.upliftly.quotes.domain.Quote
import cl.duoc.upliftly.ui.theme.UpliftlyTheme
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun QuoteCardItem(modifier: Modifier = Modifier, quote: Quote, showFullQuote:Boolean = false) {
    Card(modifier = modifier) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                     model = ImageRequest.Builder(LocalContext.current)
                    .data("https://picsum.photos/600/400/?blur=2") // Replace with your endpoint
                    .memoryCachePolicy(CachePolicy.DISABLED) // Disable memory caching
                    .diskCachePolicy(CachePolicy.DISABLED) // Disable disk caching
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = quote.quote,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(32.dp)
                    .blendMode(BlendMode.Difference),
                style = MaterialTheme.typography.headlineLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = if (showFullQuote) Int.MAX_VALUE else 4
            )
        }
    }

}

@Composable
fun QuoteCardItemList(
    modifier: Modifier = Modifier,
    quotes: List<Quote>,
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyVerticalGrid(
        columns= GridCells.Adaptive(300.dp),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = innerPadding
    ) {
        items(quotes) {
            QuoteCardItem(quote = it)
        }
    }
}

@Composable
fun Modifier.blendMode(blendMode: BlendMode): Modifier {
    return this.drawWithCache {
        val graphicsLayer = obtainGraphicsLayer()
        graphicsLayer.apply {
            record {
                drawContent()
            }
            this.blendMode = blendMode
        }
        onDrawWithContent { drawLayer(graphicsLayer) }
    }
}

@Preview
@Composable
private fun QuoteCardItemListPreview() {
    val quotes = (0..10).map {
        Quote(
            1,
            "Take time once in a while to look up at the stars for at least 5 minutes, in order to comprehend your cosmic significance",
            ""
        )
    }
    UpliftlyTheme {
        QuoteCardItemList(
            quotes = quotes,
        )
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