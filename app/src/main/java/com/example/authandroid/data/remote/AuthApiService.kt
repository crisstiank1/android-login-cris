package com.tuapp.data.remote

import com.tuapp.data.remote.dto.LoginRequestDto
import com.tuapp.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("api/auth/register")
    suspend fun register(
        @Body user: UserDto
    ): Response<UserDto>

    @POST("api/auth/login")
    suspend fun login(
        @Body body: LoginRequestDto
    ): Response<UserDto>
}
