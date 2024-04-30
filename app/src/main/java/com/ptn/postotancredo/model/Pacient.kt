package com.ptn.postotancredo.model

import com.google.firebase.database.DatabaseReference
import com.ptn.postotancredo.dataBase.FirebaseHelper

class Pacient() {
     var id: String? = null
     var nome: String? = null
     var cpf: String? = null
     var noSus: String? = null
     var email: String? = null
     var password: String? = null


     init {
         val reference = FirebaseHelper.getDatabaseReference()
          id = reference.push().key

     }

     fun saveUser(){
        val referece = FirebaseHelper.getDatabaseReference()
            .child("pacients")
            .child(id!!)
        referece.setValue(this)
    }

}


