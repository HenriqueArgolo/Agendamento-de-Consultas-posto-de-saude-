package com.ptn.postotancredo.view.atentication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ptn.postotancredo.R
import com.ptn.postotancredo.dataBase.FirebaseHelper
import com.ptn.postotancredo.databinding.ActivityLoginBinding
import com.ptn.postotancredo.view.MainActivity

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginButton.setOnClickListener {
            validaLogin()
        }
        binding.backHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.register.setOnClickListener {
            startActivity((Intent(this, Register::class.java)))
        }
    }


    private fun validaLogin(){
        val email:String = binding.emailLogin.text.toString()
        val password: String = binding.passwordLogin.text.toString()

        if(email.isEmpty() || password.isEmpty())
            Toast.makeText(this, "Preencha corretamente os campos", Toast.LENGTH_LONG).show()
        else logar(email, password)
    }

     fun logar(email: String, password: String) {
        FirebaseHelper.getAuth().signInWithEmailAndPassword(
            email, password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                val error = task.exception?.message
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()

            }
        }
    }
}
