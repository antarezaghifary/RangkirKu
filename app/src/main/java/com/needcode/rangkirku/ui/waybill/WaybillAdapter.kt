package com.needcode.rangkirku.ui.waybill

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.needcode.rangkirku.database.persistence.WaybillEntity
import com.needcode.rangkirku.databinding.AdapterWaybillBinding

class WaybillAdapter(
    val waybilles: ArrayList<WaybillEntity>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<WaybillAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterWaybillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: WaybillAdapter.ViewHolder, position: Int) {
        val waybill = waybilles[position]
        holder.binding.textWaybill.text = waybill.waybill
        holder.binding.textCourier.text = waybill.courier
        holder.binding.textStatus.text = waybill.status
        holder.binding.container.setOnClickListener {
            listener.onDetail(waybill)
        }

        holder.binding.container.setOnLongClickListener {
            listener.onDelete(waybill)
            true
        }
    }

    override fun getItemCount(): Int = waybilles.size

    class ViewHolder(val binding: AdapterWaybillBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnAdapterListener {
        fun onDetail(result: WaybillEntity)
        fun onDelete(result: WaybillEntity)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(data: List<WaybillEntity>) {
        waybilles.clear()
        waybilles.addAll(data)
        notifyDataSetChanged()
    }
}