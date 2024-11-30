package cl.duoc.upliftly.auth.presentation.login_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.duoc.upliftly.R
import cl.duoc.upliftly.ui.theme.UpliftlyTheme
import kotlin.reflect.KFunction1

@Composable
fun LoginScreenRoot(modifier: Modifier = Modifier, loginScreenViewModel: LoginScreenViewModel) {

    LoginScreen(
        modifier = Modifier.fillMaxSize(),
        email = loginScreenViewModel.email,
        password = loginScreenViewModel.password,
        onEmailChanged = loginScreenViewModel::updateEmail,
        onPasswordChanged = loginScreenViewModel::updatePassword
    )

}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit
) {
    Surface(modifier = modifier) {
        Box(contentAlignment = Alignment.Center) {
            Column(Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                UpliftlyLogo()
                Spacer(modifier = Modifier.height(48.dp))
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    TextField(
                        value = email,
                        onValueChange = onEmailChanged,
                        placeholder = { Text(text = "test@gmail.com") },
                        label = { Text(text = "Email") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next

                        ),
                        leadingIcon = {
                            Icon(
                                contentDescription = null,
                                imageVector = Icons.Default.Email
                            )
                        }
                    )
                    TextField(
                        value = password,
                        onValueChange = onPasswordChanged,
                        placeholder = { Text(text = "Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        leadingIcon = {
                            Icon(
                                contentDescription = null,
                                imageVector = Icons.Default.Lock
                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Column(
                    modifier = Modifier.padding(horizontal = 44.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Log in")
                    }
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    ) { Text(text = "Forgot your password?") }
                }
            }
        }

    }
}

@Composable
private fun UpliftlyLogo(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Login Icon",
            modifier = Modifier.size(100.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.app_name),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    UpliftlyTheme {
        LoginScreen(
            modifier = Modifier.fillMaxSize(),
            email = "",
            password = "",
            onPasswordChanged ={},
            onEmailChanged = {}
        )
    }
}