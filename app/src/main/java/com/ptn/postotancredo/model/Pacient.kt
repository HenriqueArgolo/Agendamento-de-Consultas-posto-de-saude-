package com.ptn.postotancredo.model

import com.google.firebase.database.DatabaseReference
import com.ptn.postotancredo.dataBase.FirebaseHelper

class Pacient {
    private var id: String? = null
    private var nome: String? = null
    private var idade: String? = null
    private var cpf: String? = null
    private var noSus: String? = null
    private var password: String? = null

    init {
        user()
    }
   private fun user() {
        val reference: DatabaseReference = FirebaseHelper.getDatabaseReference()
        id = reference.push().key
    }

    fun saveUser(){
        val reference : DatabaseReference = FirebaseHelper.getDatabaseReference()
            .child("pacients")
            .child(id!!)
        reference.setValue(this)
    }

}


