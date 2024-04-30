package com.ptn.postotancredo.view

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.service.autofill.UserData
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ptn.postotancredo.R
import com.ptn.postotancredo.dataBase.FirebaseHelper
import com.ptn.postotancredo.databinding.ActivityMainBinding
import com.ptn.postotancredo.databinding.FragmentMain2Binding
import com.ptn.postotancredo.model.Pacient
import com.ptn.postotancredo.view.atentication.Login
import com.ptn.postotancredo.viewModel.CalendarViewModel
import com.ptn.postotancredo.viewModel.MainViewModel
import com.ptn.postotancredo.viewModel.UserDataInfo

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMain2Binding
    private val firebaseHelper: FirebaseHelper = FirebaseHelper
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


        setUserInfo()
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
            if (firebaseHelper.isAutenticado()){
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }else{
                startActivity(Intent(requireContext(), Login::class.java))
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
    private fun setUserInfo(){
      val getUserDataInfo = UserDataInfo()
        getUserDataInfo.getAuthUser(user)

        if (!user.id.isNullOrBlank()){
            binding.userName.text = user.nome.toString()
        }else{
            binding.userName.text = "Faça login."
        }

    }
}







