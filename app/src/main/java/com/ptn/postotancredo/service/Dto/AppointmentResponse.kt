package com.ptn.postotancredo.service.Dto

import com.ptn.postotancredo.model.Procedures
import com.ptn.postotancredo.model.User

data class AppointmentResponse(
    val user: User,
    val procedures: Procedures,
    val healthCenter: String,
    val appointmentDate: String,
    val creationTimesTamp: String,
    val status: String
)
