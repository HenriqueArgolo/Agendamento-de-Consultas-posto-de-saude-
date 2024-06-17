package com.ptn.postotancredo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ptn.postotancredo.databinding.HistoryObjectBinding
import com.ptn.postotancredo.model.History

class HistoryAdapter(
    private val context: Context,
    private val historyList: List<History>
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: HistoryObjectBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(history: History){
            val user = history.user
            binding.historyUsername.text = String.format("${user.firstName} ${user.lastName}")
            binding.historyCpf.text = String.format("CPF: ${user.cpf}")
            binding.historySus.text = String.format("CPF: ${user.sus}")
            binding.hisotryProcedure.text = String.format("Procedimento: ${history.procedures }")
            binding.hisotryStatus.text = String.format("Status: ${history.status }")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HistoryObjectBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = historyList[position]
        holder.bind(history)

    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}
