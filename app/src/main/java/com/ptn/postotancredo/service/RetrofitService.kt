package com.ptn.postotancredo.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    val logginIntercetor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(logginIntercetor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.8:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val apiService = retrofit.create(ApiService::class.java)


}