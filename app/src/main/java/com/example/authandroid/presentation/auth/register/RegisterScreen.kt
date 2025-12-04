package com.example.authandroid.presentation.auth.register

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.authandroid.auth.register.RegisterViewModel
import com.example.authandroid.presentation.auth.AuthBottomLink // Import que faltaba
import com.example.authandroid.presentation.auth.AuthScaffold
import com.example.authandroid.presentation.auth.AuthTextField
// Asumo que el ViewModel está en el mismo paquete, si no, ajusta este import

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Campo local solo para "Confirmar contraseña"
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    // Navegar al terminar el registro
    LaunchedEffect(state.success) {
        if (state.success) {
            onRegisterSuccess()
            // Si tienes un viewModel.resetSuccess() puedes llamarlo aquí
        }
    }

    AuthScaffold(
        title = "Únete",
        subtitle = "Crea tu cuenta de jugador",
        content = {
            AuthTextField(
                value = state.email,
                onValueChange = { viewModel.onEmailChange(it) }, // Asumo que este método existe
                label = "Email",
                leadingIcon = Icons.Filled.Email
            )

            AuthTextField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChange(it) }, // Asumo que este método existe
                label = "Contraseña",
                leadingIcon = Icons.Filled.Lock,
                isPassword = true
            )

            AuthTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Confirmar contraseña",
                leadingIcon = Icons.Filled.Lock,
                isPassword = true
            )

            Button(
                onClick = {
                    if (state.password == confirmPassword) {
                        viewModel.register() // Asumo que este método existe
                    } else {
                        // Opcional: Mostrar error si no coinciden
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF4C6FFF)
                )
            ) {
                Text(text = "Crear Cuenta")
            }
        },
        bottomText = {
            AuthBottomLink(
                normalText = "¿Ya tienes cuenta? ",
                actionText = "Inicia Sesión",
                onClick = onNavigateToLogin
            )
        }
    )
}