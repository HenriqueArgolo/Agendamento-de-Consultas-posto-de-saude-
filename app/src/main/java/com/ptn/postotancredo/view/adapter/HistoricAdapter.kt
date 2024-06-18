package com.ptn.postotancredo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ptn.postotancredo.databinding.HistoryObjectBinding
import com.ptn.postotancredo.model.Historic

class HistoricAdapter(
    private val context: Context,
    private val historyList: List<Historic>
) : RecyclerView.Adapter<HistoricAdapter.ViewHolder>() {

    class ViewHolder(val binding: HistoryObjectBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(history: Historic){
            val user = history.user
            binding.historyUsername.text = String.format("${user.firstName} ${user.lastName}")
            binding.historyCpf.text = String.format("CPF: ${user.cpf}")
            binding.historySus.text = String.format("SUS: ${user.sus}")
            binding.hisotryProcedure.text = String.format("Tipo: ${history.procedures.name }")
            binding.hisotryStatus.text = String.format("Status: ${history.status }")
            binding.aditionalDateTime.text = history.creationTimesTamp
            binding.aditionalHelthCenter.text = history.healthCenter


            var num = 0
            binding.buttonShowMore.setOnClickListener {
                if (num == 0){
                    binding.moreInfo.visibility = View.VISIBLE
                    num = 1
                }else {
                    binding.moreInfo.visibility = View.GONE
                    num = 0
                }


            }
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
