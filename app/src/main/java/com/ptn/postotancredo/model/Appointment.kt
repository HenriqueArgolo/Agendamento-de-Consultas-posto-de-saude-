package com.ptn.postotancredo.model

import java.time.Instant

data class Appointment(
    val procedures: Procedures,
    val healthCenter: String,
    val appointmentDate: String,
    val status: String
)
