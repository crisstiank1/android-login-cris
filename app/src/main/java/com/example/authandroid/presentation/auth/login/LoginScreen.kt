package com.example.authandroid.presentation.auth.login
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.authandroid.presentation.auth.AuthBottomLink
import com.example.authandroid.presentation.auth.AuthScaffold
import com.example.authandroid.presentation.auth.AuthTextField
import com.tuapp.presentation.auth.login.LoginViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Navegar cuando el login sea exitoso
    LaunchedEffect(state.success) {
        if (state.success) {
            onLoginSuccess()
            viewModel.resetSuccess()
        }
    }

    AuthScaffold(
        title = "Game Zone",
        subtitle = "Inicia sesión para jugar",
        content = {
            AuthTextField(
                value = state.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = "Email",
                leadingIcon = Icons.Filled.Email
            )

            AuthTextField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = "Contraseña",
                leadingIcon = Icons.Filled.Lock,
                isPassword = true
            )

            Button(
                onClick = { viewModel.login() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF4C6FFF)
                )
            ) {
                Text(text = "Iniciar Sesión")
            }
        },
        bottomText = {
            AuthBottomLink(
                normalText = "¿No tienes cuenta? ",
                actionText = "Regístrate",
                onClick = onNavigateToRegister
            )
        }
    )
}