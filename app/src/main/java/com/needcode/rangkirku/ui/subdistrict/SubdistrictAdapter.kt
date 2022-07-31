package com.needcode.rangkirku.ui.subdistrict

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.needcode.rangkirku.databinding.AdapterSubdistrictBinding
import com.needcode.rangkirku.network.response.SubdistrictResponse

class SubdistrictAdapter(
    val subdistrictes: ArrayList<SubdistrictResponse.Rajaongkir.Results>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<SubdistrictAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterSubdistrictBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SubdistrictAdapter.ViewHolder, position: Int) {
        val subdistrict = subdistrictes[position]
        holder.binding.textName.text = subdistrict.subdistrict_name
        holder.binding.container.setOnClickListener {
            listener.onClick(subdistrict)
        }
    }

    override fun getItemCount(): Int = subdistrictes.size

    class ViewHolder(val binding: AdapterSubdistrictBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnAdapterListener {
        fun onClick(result: SubdistrictResponse.Rajaongkir.Results)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(data: List<SubdistrictResponse.Rajaongkir.Results>) {
        subdistrictes.clear()
        subdistrictes.addAll(data)
        notifyDataSetChanged()
    }
}