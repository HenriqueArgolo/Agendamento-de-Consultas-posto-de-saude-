package com.ptn.postotancredo.viewModel

import android.service.autofill.UserData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ptn.postotancredo.dataBase.FirebaseHelper
import com.ptn.postotancredo.model.Pacient

class UserDataInfo {
         fun getAuthUser( userDataInfo: Pacient){
            if (FirebaseHelper.isAutenticado()) {
                val database: FirebaseDatabase = FirebaseDatabase.getInstance()
                val userReference: DatabaseReference = database.getReference("pacients")
                val userAuth = FirebaseHelper.getAuth().currentUser

                if (userAuth != null) {
                    userReference.child(userAuth.uid)
                        .addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val user = snapshot.getValue(Pacient::class.java)
                                if (user != null) {
                                    userDataInfo.apply {
                                        this.id = user.id
                                        this.nome = user.nome
                                        this.cpf = user.cpf
                                        this.noSus = user.noSus
                                        this.email = user.email
                                        this.password = user.password
                                    }
                                }
                            }
                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        })
                }
            }}

    }