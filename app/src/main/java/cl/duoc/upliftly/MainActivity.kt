package cl.duoc.upliftly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import cl.duoc.upliftly.quotes.presentation.AppScaffold
import cl.duoc.upliftly.ui.theme.UpliftlyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UpliftlyTheme {
                AppScaffold(modifier = Modifier.fillMaxSize())
            }
        }
    }
}





