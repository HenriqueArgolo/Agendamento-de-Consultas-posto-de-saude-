package com.ptn.postotancredo.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ptn.postotancredo.databinding.ActivityLoginBinding
import com.ptn.postotancredo.service.Dto.LoginResquest
import com.ptn.postotancredo.service.auth.GlobalTokenValue
import com.ptn.postotancredo.service.auth.Login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("token_prefs", MODE_PRIVATE)

        binding.loginButton.setOnClickListener {
            signIn()
        }

    }
    fun signIn() {
        val email = binding.emailLogin.text.toString()
        val password = binding.passwordLogin.text.toString()
        val login = Login(sharedPreferences)
        CoroutineScope(Dispatchers.Main).launch{
            login.signIn(email, password)
            if (GlobalTokenValue.tokenValue.isNotBlank()){
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                Log.d("loginActivity" ,"esse é o tokeeeeeeee: ${GlobalTokenValue.tokenValue}")
            }else
                Toast.makeText(this@LoginActivity, "Falha no login", Toast.LENGTH_LONG).show()
            }
        }
}