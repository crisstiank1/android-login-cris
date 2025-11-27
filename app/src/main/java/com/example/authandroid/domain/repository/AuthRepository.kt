// domain/repository/AuthRepository.kt
package com.tuapp.domain.repository

import com.tuapp.domain.model.User

interface AuthRepository {

    suspend fun register(
        name: String,
        email: String,
        password: String
    ): User

    suspend fun login(
        email: String,
        password: String
    ): User
}
