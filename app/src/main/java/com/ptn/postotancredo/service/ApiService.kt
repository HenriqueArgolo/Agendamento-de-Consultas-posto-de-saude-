package com.ptn.postotancredo.service

import com.ptn.postotancredo.service.Dto.LoginResquest
import com.ptn.postotancredo.service.Dto.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/login")
    suspend fun login(@Body loginResquest: LoginResquest): Response<TokenResponse>
}