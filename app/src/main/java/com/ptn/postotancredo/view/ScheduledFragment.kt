package com.ptn.postotancredo.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ptn.postotancredo.R
import com.ptn.postotancredo.databinding.FragmentScheduledBinding
import com.ptn.postotancredo.service.auth.GlobalTokenValue
import com.ptn.postotancredo.viewModel.ScheduledViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduledFragment : Fragment() {
    private lateinit var binding: FragmentScheduledBinding
    private val scheduledViewModel: ScheduledViewModel by viewModels()

    companion object {
        fun newInstance() = ScheduledFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduledBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindAppointment()
    }

    private fun bindAppointment() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val appointmentData = scheduledViewModel.getAppointment()
                withContext(Dispatchers.Main) {
                    if (appointmentData != null && appointmentData.status != "finalizado") {
                        val fullName = "${appointmentData.user.firstName} ${appointmentData.user.lastName}"
                        val cpf = appointmentData.user.cpf
                        val sus = appointmentData.user.sus
                        binding.appointmentUsername.text = fullName
                        binding.appointmentCpf.text = "CPF: $cpf"
                        binding.appointmentSus.text = "SUS: $sus"
                        binding.appointmentProcedure.text = "Tipo: ${appointmentData.procedures.name}"
                        binding.appointmentDate.text = "Data: ${appointmentData.appointmentDate}"
                        visibile()
                    } else {
                        gone()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    gone()
                }
            }
        }
    }

    private fun visibile() {
        binding.appointmentContainer.visibility = View.VISIBLE
        binding.cancelAppointmentBtn.visibility = View.VISIBLE
        binding.appointmentMessage.visibility = View.VISIBLE
    }

    private fun gone() {
        binding.appointmentContainer.visibility = View.GONE
        binding.cancelAppointmentBtn.visibility = View.GONE
        binding.appointmentMessage.visibility = View.GONE
    }
}