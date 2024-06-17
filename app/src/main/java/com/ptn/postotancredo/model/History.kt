package com.ptn.postotancredo.model

import java.time.Instant

data class History(
    val id: Long,
    val user: User,
    val procedures: Procedures,
    var healthCenter: String,
    var appointmentDate: String,
    var creationTimesTamp: Instant,
    var status: String
)
