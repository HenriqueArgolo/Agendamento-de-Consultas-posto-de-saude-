package com.ptn.postotancredo.view.atentication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ptn.postotancredo.R
import com.ptn.postotancredo.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_login)


       navigationThroughtActivities()

    }


    private fun navigationThroughtActivities(){
        binding.backButton.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
        binding.register.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }

    private fun login(){

    }
}