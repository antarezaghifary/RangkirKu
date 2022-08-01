package com.needcode.rangkirku.ui.cost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.needcode.rangkirku.databinding.AdapterServiceBinding
import com.needcode.rangkirku.network.response.CostResponse

class ServiceAdapter(
    val costes: List<CostResponse.Rajaongkir.Results.Cost>
) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ServiceAdapter.ViewHolder, position: Int) {
        val cost = costes[position]
        holder.binding.textService.text = cost.service
        holder.binding.textDescription.text = cost.description
        holder.binding.textValue.text = cost.cost.get(0).value.toString()
        holder.binding.textEtd.text = cost.cost.get(0).etd
        //holder.binding.listService.adapter =

    }

    override fun getItemCount(): Int = costes.size

    class ViewHolder(val binding: AdapterServiceBinding) : RecyclerView.ViewHolder(binding.root)

}