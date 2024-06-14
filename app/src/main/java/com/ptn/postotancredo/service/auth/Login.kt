package com.ptn.postotancredo.service.auth

import com.ptn.postotancredo.service.RetrofitService
import com.ptn.postotancredo.service.Dto.LoginResquest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.ptn.postotancredo.service.Dto.UserDataResponse


class Login(private val sharedPreferences: SharedPreferences) {

    private val gson = Gson()
    fun signIn(username: String, password: String) {
        val loginRequest = LoginResquest(username, password)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitService().apiService.login(loginRequest)
                if (response.isSuccessful) {
                    response.body()?.let {
                        saveUserData(it)
                    }
                }
            } catch (e: Exception) {
                Log.e("Login", "Erro ao fazer login", e)

            }
        }
    }
    private fun saveUserData(userData: UserDataResponse) {
        val userLoginResponse = gson.toJson(userData)
        sharedPreferences.edit().putString("userData", userLoginResponse).apply()
        val user = sharedPreferences.getString("userData", null).let { gson.fromJson(it, UserDataResponse::class.java) }
        GlobalTokenValue.initUserData(user)
    }
}