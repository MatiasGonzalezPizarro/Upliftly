package cl.duoc.upliftly.quotes.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.duoc.upliftly.R
import cl.duoc.upliftly.quotes.domain.Quote
import cl.duoc.upliftly.quotes.presentation.discover_screen.DiscoverAdviceRoute
import cl.duoc.upliftly.quotes.presentation.discover_screen.DiscoverAdviceScreen
import cl.duoc.upliftly.quotes.presentation.discover_screen.DiscoverAdviceViewModel
import cl.duoc.upliftly.quotes.presentation.home_screen.QuoteCardItemList
import cl.duoc.upliftly.ui.theme.UpliftlyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppScaffold(modifier: Modifier = Modifier) {
    val quotes = (0..10).map {
        Quote(
            1,
            "Take time once in a while to look up at the stars for at least 5 minutes, in order to comprehend your cosmic significance",

        )
    }
    val tabs = listOf(
        NavigationDestination(label = "Favorite", icon = Icons.Default.Favorite),
        NavigationDestination(label = "Advices", icon = Icons.Default.Accessibility),
        NavigationDestination(label = "Profile", icon = Icons.Default.Person)
    )
    var currentTab by rememberSaveable { mutableIntStateOf(0) }
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
            modifier = modifier
        ) { innerPadding ->
            Box(
                Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when(currentTab){
                    0-> QuoteCardItemList(
                        quotes = quotes,
                        modifier = Modifier.padding(horizontal = 12.dp),
                        innerPadding = innerPadding
                    )
                    1-> DiscoverAdviceRoute(viewModel = koinViewModel<DiscoverAdviceViewModel>())
                    2-> Text(text="PELAO")
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