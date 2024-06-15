package com.ptn.postotancredo.service

import com.ptn.postotancredo.model.Appointment
import com.ptn.postotancredo.model.User
import com.ptn.postotancredo.service.Dto.AppointmentResponse
import com.ptn.postotancredo.service.Dto.LoginResquest
import com.ptn.postotancredo.service.Dto.UserDataResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/login")
    suspend fun login(@Body loginResquest: LoginResquest): Response<UserDataResponse>

    @POST("/api/appointment")
    suspend fun creatAppointment(
            @Header("Authorization") token: String,
            @Body appointment: Appointment
        ): Response<Appointment>

    @GET("/api/userappointment")
    suspend fun getUserAppointment(@Header("Authorization") token: String?):Response<AppointmentResponse>

    @POST("/api/checkifscheduled")
    suspend fun isScheduled(@Header("Authorization") token: String, @Body user: User):Boolean
}