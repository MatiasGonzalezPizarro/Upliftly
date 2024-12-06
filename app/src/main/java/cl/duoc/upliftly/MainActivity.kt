package cl.duoc.upliftly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import cl.duoc.upliftly.di.module
import cl.duoc.upliftly.quotes.presentation.AppScaffold
import cl.duoc.upliftly.ui.theme.UpliftlyTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UpliftlyTheme {
                KoinApplication(
                    application = {
                        modules(module)
                        androidContext(applicationContext)
                    }
                ) {

                    AppScaffold(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

        currentUser?.let { authenticatedUser ->
            // Logica si el usuario esta autenticado (si currentUser no es null)

        }
    }
}





