package com.ptn.postotancredo.model

import java.time.Instant

data class Appointment(
    var procedures: Procedures,
    var healthCenter: String,
    var appointmentDate: String,
    var status: String
)
