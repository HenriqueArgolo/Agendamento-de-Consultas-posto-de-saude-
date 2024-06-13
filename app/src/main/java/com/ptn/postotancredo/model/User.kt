package com.ptn.postotancredo.model

class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val sus: String,
    val userName: String,
    val email: String,
    val password: String,
    val roles: List<Role>
) {


}


