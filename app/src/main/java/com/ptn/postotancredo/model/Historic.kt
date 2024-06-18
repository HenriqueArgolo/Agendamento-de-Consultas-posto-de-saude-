package com.ptn.postotancredo.model

import java.time.Instant

data class Historic(
    val id: Long,
    val user: User,
    val procedures: Procedures,
    var healthCenter: String,
    var appointmentDate: String,
    var creationTimesTamp: Instant,
    var status: String
)
