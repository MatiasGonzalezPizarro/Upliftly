package cl.duoc.upliftly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.duoc.upliftly.auth.presentation.login_screen.LoginScreen
import cl.duoc.upliftly.auth.presentation.login_screen.LoginScreenRoot
import cl.duoc.upliftly.auth.presentation.login_screen.LoginScreenViewModel
import cl.duoc.upliftly.quotes.domain.Quote
import cl.duoc.upliftly.quotes.presentation.QuoteCardItem
import cl.duoc.upliftly.ui.theme.UpliftlyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
    }
}





