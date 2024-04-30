package com.ptn.postotancredo.view.atentication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ptn.postotancredo.R
import com.ptn.postotancredo.dataBase.FirebaseHelper
import com.ptn.postotancredo.databinding.ActivityRegisterBinding
import com.ptn.postotancredo.model.Pacient
import com.ptn.postotancredo.view.MainActivity
import java.util.Objects

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    val login = Login()
    var user = Pacient()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationThroughtActivities()
    }

    private fun navigationThroughtActivities() {
        binding.registerButton.setOnClickListener {
            validateData(user)
        }
        binding.backButton.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }

    private fun validateData(user1: Pacient) {
        val cpf: String = binding.cpfRegister.text.toString()
        val fullName: String = binding.fullName.text.toString()
        val susNumber: String = binding.susRegister.text.toString()
        val email: String = binding.emailRegister.text.toString()
        val password: String = binding.passwordRegister.text.toString()
        val repeatPassword: String = binding.repeatPassword.text.toString()

        val allFilled = listOf(
            cpf,
            fullName,
            susNumber,
            email,
            password,
            repeatPassword
        ).all { it.isNotBlank() }

        if (allFilled) {
            if (password == repeatPassword) {
                if (cpf.length == 11) {
                    if (susNumber.length == 15) {
                        user1.apply {
                            this.cpf = cpf
                            this.nome = fullName
                            this.noSus = susNumber
                            this.email = email
                            this.password = password
                        }
                        creteUser(user1)
                        user1.saveUser()
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        binding.susRegister.requestFocus()
                        binding.susRegister.error = "Número do SUS inválido"
                    }
                } else {
                    binding.cpfRegister.requestFocus()
                    binding.cpfRegister.error = "Número de CPF inválido"
                }
            } else {
                binding.passwordRegister.requestFocus()
                binding.repeatPassword.requestFocus()
                binding.passwordRegister.error = "As senhas não coincidem"
            }
        }
    }

    private fun creteUser(user1: Pacient) {
        FirebaseHelper.getAuth().createUserWithEmailAndPassword(
            user1.email!!, user1.password!!
        ).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                login.logar(user.email!!, user.password!!)
            } else {
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
            }
        }
    }


}