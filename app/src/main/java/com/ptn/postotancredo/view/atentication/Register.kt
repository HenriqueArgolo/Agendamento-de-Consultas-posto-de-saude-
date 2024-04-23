package com.ptn.postotancredo.view.atentication

import android.content.Intent
import android.os.Bundle
import android.view.View
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

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    val login = Login()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navigationThroughtActivities()
    }

    private fun navigationThroughtActivities() {
        binding.registerButton.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
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
                        val user = Pacient().apply {
                            this.cpf = cpf
                            this.nome = fullName
                            this.noSus = susNumber
                            this.email = email
                            this.password = password
                        }
                        user.saveUser()
                        creteUserLogin(user)
                    } else
                        binding.susRegister.requestFocus()
                    binding.susRegister.error = "Número do SUS inválido"
                } else
                    binding.cpfRegister.requestFocus()
                binding.cpfRegister.error = "Número de CPF inválido"

            } else
                binding.passwordRegister.requestFocus()
            binding.repeatPassword.requestFocus()
            binding.passwordRegister.error = "As senhas não coicidem"

        }
    }

    private fun creteUserLogin(user: Pacient) {
        FirebaseHelper.getAuth().createUserWithEmailAndPassword(
            user.email!!, user.password!!
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                login.logar(user.email!!, user.password!!)
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
            }
        }
    }


}