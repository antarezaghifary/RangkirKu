package com.needcode.rangkirku.ui.cost

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.needcode.rangkirku.databinding.AdapterCostBinding
import com.needcode.rangkirku.network.response.CostResponse

class CostAdapter(
    val costes: ArrayList<CostResponse.Rajaongkir.Results>
) : RecyclerView.Adapter<CostAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterCostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cost = costes[position]
        holder.binding.textCode.text = cost.code
        holder.binding.textName.text = cost.name
        holder.binding.listService.adapter = ServiceAdapter(cost.costs)

    }

    override fun getItemCount(): Int = costes.size

    class ViewHolder(val binding: AdapterCostBinding) : RecyclerView.ViewHolder(binding.root)


    @SuppressLint("NotifyDataSetChanged")
    fun addData(data: List<CostResponse.Rajaongkir.Results>) {
        costes.clear()
        costes.addAll(data)
        notifyDataSetChanged()
    }
}