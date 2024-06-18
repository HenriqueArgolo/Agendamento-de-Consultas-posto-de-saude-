package com.ptn.postotancredo.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ptn.postotancredo.R
import com.ptn.postotancredo.databinding.FragmentHistoryBinding
import com.ptn.postotancredo.databinding.FragmentMain2Binding
import com.ptn.postotancredo.model.Historic
import com.ptn.postotancredo.service.RetrofitService
import com.ptn.postotancredo.service.auth.GlobalTokenValue
import com.ptn.postotancredo.view.adapter.HistoricAdapter
import com.ptn.postotancredo.viewModel.HistoryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoricFragment : Fragment() {
    lateinit var binding: FragmentHistoryBinding
    private var historicList: List<Historic> = emptyList()
    lateinit var historicAdapter: HistoricAdapter

    companion object {
        fun newInstance() = HistoricFragment()
    }

    private val viewModel: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHistoryBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchAndDisplayHistoric()
    }

    private fun fetchAndDisplayHistoric() {
        CoroutineScope(Dispatchers.Main).launch {
            historicList = getHistoric()
            configRv()
        }
    }

    private fun configRv() {
        if (historicList.isEmpty()) {
            binding.rv.visibility = View.GONE
            Log.d("HiscoricFragment", "the historic list is empity")
        }
        historicAdapter = HistoricAdapter(requireContext(), historicList)
        binding.rv.apply {
            adapter = historicAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private suspend fun getHistoric(): List<Historic> {
        val token = GlobalTokenValue.userDataResponse?.accessToken ?: run {
            Log.d("Histic Fragment", "Token not found")
            return emptyList()
        }
        return withContext(Dispatchers.Main) {
            val response = RetrofitService().apiService.getHistoric("Bearer $token")
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else emptyList()
        }
    }
}


