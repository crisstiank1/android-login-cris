// domain/usecase/RegisterUseCase.kt
package com.tuapp.domain.usecase

import com.tuapp.core.util.Result
import com.tuapp.domain.model.User
import com.tuapp.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        name: String,
        email: String,
        password: String
    ): Result<User> {
        return try {
            val user = repository.register(name, email, password)
            Result.Success(user)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Error inesperado")
        }
    }
}
