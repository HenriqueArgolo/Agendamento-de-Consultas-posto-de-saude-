package com.ptn.postotancredo.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ptn.postotancredo.dataBase.FirebaseHelper
import com.ptn.postotancredo.databinding.FragmentMain2Binding
import com.ptn.postotancredo.model.Pacient
import com.ptn.postotancredo.service.auth.GlobalTokenValue
import com.ptn.postotancredo.viewModel.CalendarViewModel
import com.ptn.postotancredo.viewModel.MainViewModel
import com.ptn.postotancredo.viewModel.UserDataInfo

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMain2Binding
    lateinit var sharedPreferences: SharedPreferences
    private val calendarViewModel = CalendarViewModel()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private var user: Pacient = Pacient()

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


        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.peekHeight = 0



        configClicks()
        checkBottomSheet()
        closeSchueldingScreen()
        calendarViewModel.formatedDate(binding.daySelected, binding.calendar)
        binding.closeButton.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

    }

    private fun configClicks() {
        binding.appointment.setOnClickListener {
            if (GlobalTokenValue.tokenValue.isNotBlank()){
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }else{
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
        }
    }



}







