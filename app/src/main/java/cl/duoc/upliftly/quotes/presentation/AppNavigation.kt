package cl.duoc.upliftly.quotes.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cl.duoc.upliftly.R
import cl.duoc.upliftly.quotes.presentation.discover_screen.DiscoverAdviceViewModel
import cl.duoc.upliftly.quotes.presentation.home_screen.QuoteCardItem
import cl.duoc.upliftly.quotes.presentation.home_screen.QuoteCardItemList
import cl.duoc.upliftly.ui.theme.UpliftlyTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppScaffold(modifier: Modifier = Modifier) {
    val tabs = listOf(
        NavigationDestination(label = "Favorite", icon = Icons.Default.Favorite),
        NavigationDestination(label = "Advices", icon = Icons.Default.Accessibility),
        NavigationDestination(label = "Profile", icon = Icons.Default.Person)
    )
    var currentTab by rememberSaveable { mutableIntStateOf(0) }
    val discoverAdviceViewModel = koinViewModel<DiscoverAdviceViewModel>()
    val favoriteQuotes = discoverAdviceViewModel.uiState.collectAsStateWithLifecycle()
    val currentAdvice = discoverAdviceViewModel.currentAdvice.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val pagerState = rememberPagerState { 5 }
    val coroutineScope = rememberCoroutineScope()

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            tabs.forEachIndexed { index, navigationDestination ->
                item(
                    icon = { Icon(navigationDestination.icon, contentDescription = null) },
                    label = { Text(navigationDestination.label) },
                    selected = currentTab == index,
                    onClick = { currentTab = index }
                )
            }
        }
    ) {
        Scaffold(
            topBar = { TopBar() },
            modifier = modifier,
            floatingActionButton = {
                AnimatedVisibility(currentTab == 1) {
                    Column {
                        FloatingActionButton(onClick = {
                            coroutineScope.launch {
                                val currentPage = pagerState.currentPage
                                val currentQuote = favoriteQuotes.value.quotes.getOrNull(currentPage)
                                currentQuote?.let { discoverAdviceViewModel.onAdviceFavorited(it) }
                            }
                        }) {
                            Icon(
                                imageVector = if (currentAdvice.value?.isFavorite == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "favorite"
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        FloatingActionButton(onClick = {
                            coroutineScope.launch {
                                val currentPage = pagerState.currentPage
                                val currentQuote = favoriteQuotes.value.quotes.getOrNull(currentPage)
                                currentQuote?.let {
                                    context.startActivity(discoverAdviceViewModel.onAdviceShared(it))
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "share"
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Log.i("AppScaffold", "favoriteQuotes: ${favoriteQuotes.value.favorites}")
            Box(
                Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (currentTab) {
                    0 -> QuoteCardItemList(
                        quotes = favoriteQuotes.value.favorites,
                        modifier = Modifier.padding(horizontal = 12.dp),
                        innerPadding = innerPadding
                    )

                    1 -> {
                        VerticalPager(state = pagerState) { page ->
                            val quote = favoriteQuotes.value.quotes.getOrNull(page)
                            LaunchedEffect(page) {
                                discoverAdviceViewModel.updateCurrentAdvice(quote)
                            }
                            if (quote == null) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    CircularProgressIndicator()
                                }
                            } else {
                                QuoteCardItem(
                                    modifier = Modifier.fillMaxSize(),
                                    quote = quote,
                                    showFullQuote = true,
                                    cachedImage = true,
                                    showLocalImage = true
                                )
                            }
                        }
                    }
                    2 -> Text("HOLA")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                text = stringResource(R.string.app_name)
            )
        },
        modifier = modifier
    )

}

data class NavigationDestination(
    val label: String,
    val icon: ImageVector
)


@Preview
@Composable
private fun HomePreview() {
    UpliftlyTheme { AppScaffold(Modifier.fillMaxSize()) }

}