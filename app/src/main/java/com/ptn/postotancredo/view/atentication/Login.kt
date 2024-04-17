package com.ptn.postotancredo.view.atentication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ptn.postotancredo.R
import com.ptn.postotancredo.databinding.ActivityLoginBinding
import com.ptn.postotancredo.view.MainActivity

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_login)



    }

    fun back(view: View) {
        view.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun register(view: View) {
        view.setOnClickListener {
            startActivity((Intent(this, Register::class.java)))
        }
    }
}
