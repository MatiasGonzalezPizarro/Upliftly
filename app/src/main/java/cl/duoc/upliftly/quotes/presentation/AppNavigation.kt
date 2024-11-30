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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import cl.duoc.upliftly.R
import cl.duoc.upliftly.ui.theme.UpliftlyTheme

@Composable
fun AppScaffold(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar() },
        modifier = modifier
    ) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) { Text(text = "hola") }
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

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    val tabs = listOf(
        NavigationDestination(label = "Favorite", icon = Icons.Default.Favorite),
        NavigationDestination(label = "Advices", icon = Icons.Default.Accessibility),
        NavigationDestination(label = "Profile", icon = Icons.Default.Person)
    )
    var currentTab by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar {
        tabs.forEachIndexed { index, navigationDestination ->
            NavigationBarItem(selected = index == currentTab,
                onClick = { currentTab = index },
                icon = {
                    Icon(
                        imageVector = navigationDestination.icon,
                        contentDescription = null
                    )
                },
                label= { Text(text=navigationDestination.label) }
            )
        }

    }

}

@Preview
@Composable
private fun HomePreview() {
    UpliftlyTheme { AppScaffold(Modifier.fillMaxSize()) }

}