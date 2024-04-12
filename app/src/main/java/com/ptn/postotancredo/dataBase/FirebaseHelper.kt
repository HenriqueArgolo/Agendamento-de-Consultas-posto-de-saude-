package com.ptn.postotancredo.dataBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object FirebaseHelper {
    private var auth: FirebaseAuth? = null
    private var databaseReference: DatabaseReference? = null
    private var storageReference: StorageReference? = null

    fun getDatabaseReference(): DatabaseReference {
        if (databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().reference
        }
        return databaseReference!!
    }

    fun getUserId(): String? {
        return getAuth().uid
    }

    fun getAuth(): FirebaseAuth {
        if (auth == null) {
            auth = FirebaseAuth.getInstance()
        }
        return auth!!
    }

    fun getStorageReference(): StorageReference {
        if (storageReference == null) {
            storageReference = FirebaseStorage.getInstance().reference
        }
        return storageReference!!
    }

    fun isAutenticado(): Boolean {
        return getAuth().currentUser != null
    }
}