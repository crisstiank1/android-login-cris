package com.tuapp.domain.usecase

import com.tuapp.core.util.Result
import com.tuapp.domain.model.User
import com.tuapp.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<User> {
        return try {
            val user = repository.login(email, password)
            Result.Success(user)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Error inesperado")
        }
    }
}
