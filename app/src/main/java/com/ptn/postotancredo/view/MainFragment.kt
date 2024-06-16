package com.ptn.postotancredo.view

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ptn.postotancredo.databinding.FragmentMain2Binding
import com.ptn.postotancredo.model.Appointment
import com.ptn.postotancredo.model.Procedures
import com.ptn.postotancredo.model.User
import com.ptn.postotancredo.service.RetrofitService
import com.ptn.postotancredo.service.auth.GlobalTokenValue
import com.ptn.postotancredo.viewModel.CalendarViewModel
import com.ptn.postotancredo.viewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {
    lateinit var procedure: Procedures
    var type: String = ""
    private lateinit var binding: FragmentMain2Binding
    private val calendarViewModel = CalendarViewModel()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>


    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMain2Binding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userName.text = GlobalTokenValue.userDataResponse?.accessToken
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.peekHeight = 0

        setUserInfo()
        configClicks()
        checkBottomSheet()
        closeSchueldingScreen()
        calendarViewModel.formatedDate(binding.daySelected, binding.calendar)

        binding.closeButton.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        binding.scheduel.setOnClickListener {
            saveAppointment()
        }

    }

    private fun configClicks() {
        binding.appointment.setOnClickListener {
            if (GlobalTokenValue.userDataResponse != null) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                type = "consulta"
                Log.d(
                    "main fragment",
                    "aaaaaaaaaaaaaaaaaaa: ${GlobalTokenValue.userDataResponse?.user?.firstName}"
                )
            } else {
                startActivity(Intent(requireContext(), LoginActivity::class.java))
            }
        }
    }

    private fun checkBottomSheet() {
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.peekHeight = 0

        }
    }

    private fun closeSchueldingScreen() {
        binding.closeButton.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            calendarViewModel.formatedDate(binding.daySelected, binding.calendar)

        }
    }

    private fun setUserInfo() {
        val pacient: User? = GlobalTokenValue.userDataResponse?.user
        val fullName = String.format("${pacient?.firstName} ${pacient?.lastName} ")
        binding.userName.text = fullName
        binding.userSusNumber.text = String.format("Sus: ${pacient?.sus}")
    }


    private fun saveAppointment() {
        val token = GlobalTokenValue.userDataResponse?.accessToken ?: run {
            Log.e("MainFragment", "Token is null. Cannot proceed with appointment registration.")
            return
        }
        val user = if (GlobalTokenValue.userDataResponse?.user != null)
            GlobalTokenValue.userDataResponse?.user else return

        val appointment = getAppointment()

        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitService().apiService.isScheduled("Bearer $token", user!!)
            if(!response){
                createAppointmentRequest(token, appointment)
            }else {
                GlobalScope.launch(Dispatchers.Main){
                    Toast.makeText(requireContext(), "Você já possui um agendamento.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private suspend fun createAppointmentRequest(token: String, appointment: Appointment) {
        val response = RetrofitService().apiService.creatAppointment("Bearer $token", appointment)
        withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            } else {
                Log.e("MainFragment", "Error creating appointment: ${response.code()}")
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    private fun getAppointment(): Appointment {
        val healthyCenter = "corte de pedra"
        val status = "Agendado"
        val dateSelected = binding.daySelected.text.toString()
        procedure = Procedures(type)
        return Appointment(procedure, healthyCenter, dateSelected, status)
    }

    private fun cancelAppointment() {

    }

}







