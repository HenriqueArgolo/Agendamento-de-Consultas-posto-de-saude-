package com.ptn.postotancredo.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ptn.postotancredo.R
import com.ptn.postotancredo.databinding.ActivityMainBinding
import com.ptn.postotancredo.databinding.FragmentMain2Binding
import com.ptn.postotancredo.viewModel.CalendarViewModel
import com.ptn.postotancredo.viewModel.MainViewModel

class MainFragment : Fragment() {
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
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.peekHeight = 0


        configClicks()
        checkBottomSheet()
        calendarViewModel.formatedDate(binding.daySelected, binding.calendar)
        binding.closeButton.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

    }

    private fun configClicks() {
        binding.appointment.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            bottomSheetBehavior.peekHeight = 0
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

