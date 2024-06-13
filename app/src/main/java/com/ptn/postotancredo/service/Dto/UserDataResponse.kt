package com.ptn.postotancredo.service.Dto

import com.ptn.postotancredo.model.User

data class UserDataResponse(
    val user: User,
    val accessToken: String,
    val expiresIn: Int,
    val role: String
)
