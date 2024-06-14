package com.ptn.postotancredo.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
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
import kotlinx.coroutines.launch
import retrofit2.Retrofit

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

        fragmentData()
        setUserInfo()
        configClicks()
        checkBottomSheet()
        closeSchueldingScreen()
        calendarViewModel.formatedDate(binding.daySelected, binding.calendar)

        binding.closeButton.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        binding.scheduel.setOnClickListener {
            registerAppointment()
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


    private fun registerAppointment() {
        val token = GlobalTokenValue.userDataResponse?.accessToken
        val healthyCenter = "corte de pedra"
        val status = "Agendado"
        val dateSelected = binding.daySelected.text.toString()
        procedure = Procedures(type)
        val appointment = Appointment(procedure, healthyCenter, dateSelected, status)

        CoroutineScope(Dispatchers.IO).launch {
            if (token != null) {
                try {
                    val respose = RetrofitService().apiService.creatAppointment("Bearer $token", appointment)
                    if(respose.isSuccessful){
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    }

                }catch (e: Exception){
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    Log.e("Main fragment", "não foi posivel exeecutarrrrrrrrrrr", e)
                }

            }
        }
    }

    private fun fragmentData(){
        val message = "Faça login."
        if(GlobalTokenValue.userDataResponse == null){
            binding.userName.text = String.format(" $message")
            binding.userSusNumber.visibility = View.GONE
        }
    }

    private fun cancelAppointment(){

    }
}







