// presentation/auth/register/RegisterUiState.kt
package com.tuapp.presentation.auth.register

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val success: Boolean = false
)
