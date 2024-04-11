package com.ptn.postotancredo.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ptn.postotancredo.R
import com.ptn.postotancredo.viewModel.ScheduledViewModel

class ScheduledFragment : Fragment() {

    companion object {
        fun newInstance() = ScheduledFragment()
    }

    private val viewModel: ScheduledViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_scheduled, container, false)
    }
}