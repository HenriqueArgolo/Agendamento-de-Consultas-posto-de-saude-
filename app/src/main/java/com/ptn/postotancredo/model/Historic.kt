package com.ptn.postotancredo.model

import com.google.gson.annotations.SerializedName
import java.time.Instant

data class Historic(
    @SerializedName("id")
    val id: Long,
    @SerializedName("user")
    val user: User,
    @SerializedName("procedures")
    val procedures: Procedures,
    @SerializedName("healthCenter")
    val healthCenter: String,
    @SerializedName("appointmentDate")
    val appointmentDate: String,
    @SerializedName("creationTimesTamp")
    val creationTimesTamp: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("observation")
    val observation: String

)
