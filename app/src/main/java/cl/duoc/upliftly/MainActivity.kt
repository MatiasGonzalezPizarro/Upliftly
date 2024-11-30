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
import cl.duoc.upliftly.ui.theme.UpliftlyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UpliftlyTheme {
                val loginScreenViewModel= viewModel<LoginScreenViewModel>()
                LoginScreenRoot(loginScreenViewModel = loginScreenViewModel)
            }
        }
    }
}





