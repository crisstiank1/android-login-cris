// data/mapper/UserMapper.kt
package com.tuapp.data.mapper

import com.tuapp.data.remote.dto.UserDto
import com.tuapp.domain.model.User

fun UserDto.toDomain(): User =
    User(
        id = id,
        name = name,
        email = email
    )
