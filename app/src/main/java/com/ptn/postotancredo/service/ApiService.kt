package com.ptn.postotancredo.service

import com.ptn.postotancredo.service.loginRequest.LoginResquest
import com.ptn.postotancredo.service.loginRequest.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body loginResquest: LoginResquest): Response<TokenResponse>
}