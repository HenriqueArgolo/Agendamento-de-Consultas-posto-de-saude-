package com.ptn.postotancredo.model

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id")
    val id: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("cpf")
    val cpf: String,
    @SerializedName("sus")
    val sus: String,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("roles")
    val roles: List<Role>
) {


}


