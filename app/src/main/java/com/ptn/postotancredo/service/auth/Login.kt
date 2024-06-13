package com.ptn.postotancredo.service.auth

import com.ptn.postotancredo.service.RetrofitService
import com.ptn.postotancredo.service.Dto.LoginResquest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.content.SharedPreferences
import android.util.Log


class Login(private val sharedPreferences: SharedPreferences) {


    fun signIn(username: String, password: String) {
        val loginRequest = LoginResquest(username, password)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitService().apiService.login(loginRequest)
                if (response.isSuccessful && !response.body()?.accessToken.isNullOrBlank()) {
                    response.body()?.accessToken.let {
                        if (it != null) {
                            saveToken(it)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("Login", "Erro ao fazer login", e)

            }
        }
    }
    private fun saveToken(token: String) {
        sharedPreferences.edit().putString("token_jwt", token).apply()
    }
}