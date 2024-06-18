package com.ptn.postotancredo.model

import com.google.gson.annotations.SerializedName

data class Role(
    @SerializedName("roleId")
    val roleId: Int,
    @SerializedName("name")
    val name: String
)
