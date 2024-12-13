package cl.duoc.upliftly.quotes.presentation.profile_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cl.duoc.upliftly.R
import cl.duoc.upliftly.auth.presentation.login_screen.UpliftlyLogo
import cl.duoc.upliftly.ui.theme.UpliftlyTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, count: Int = 0) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        UpliftlyLogo(text = stringResource(R.string.favorites, count))

    }
}


@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    UpliftlyTheme {
        ProfileScreen(
            modifier = Modifier.fillMaxWidth()
        )
    }

}