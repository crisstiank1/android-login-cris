// data/repository/AuthRepositoryImpl.kt
package com.tuapp.data.repository

import com.tuapp.data.mapper.toDomain
import com.tuapp.data.remote.AuthApiService
import com.tuapp.data.remote.dto.LoginRequestDto
import com.tuapp.data.remote.dto.UserDto
import com.tuapp.domain.model.User
import com.tuapp.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApiService
) : AuthRepository {

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): User {
        val body = UserDto(
            id = null,
            name = name,
            email = email,
            password = password
        )

        val response = api.register(body)
        if (response.isSuccessful) {
            val userDto = response.body()
                ?: throw IllegalStateException("Respuesta vacía del servidor")
            return userDto.toDomain()
        } else {
            throw HttpException(response)
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): User {
        val body = LoginRequestDto(email = email, password = password)

        val response = api.login(body)
        if (response.isSuccessful) {
            val userDto = response.body()
                ?: throw IllegalStateException("Respuesta vacía del servidor")
            return userDto.toDomain()
        } else {
            throw HttpException(response)
        }
    }
}
