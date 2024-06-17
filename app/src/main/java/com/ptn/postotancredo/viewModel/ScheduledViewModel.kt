package com.ptn.postotancredo.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ptn.postotancredo.service.Dto.AppointmentResponse
import com.ptn.postotancredo.service.RetrofitService
import com.ptn.postotancredo.service.auth.GlobalTokenValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ScheduledViewModel : ViewModel() {
    suspend fun getAppointment(): AppointmentResponse?{
        val token = GlobalTokenValue.userDataResponse?.accessToken

        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitService().apiService.getUserAppointment("Bearer $token")
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                Log.e("Appointment", "erro ao obter o appointment", e)
                null
            }
        }
    }

    suspend fun deleteAppointment(){
        val token = GlobalTokenValue.userDataResponse?.accessToken
            try {
                RetrofitService().apiService.deleteAppointment("Bearer $token")
            }catch (e: Exception){
                Log.e("HistoryViewModel", "impossible to delete appointmente", e)
            }

        }
    }

