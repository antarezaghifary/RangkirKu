package com.needcode.rangkirku.ui.trackingresult

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.needcode.rangkirku.databinding.AdapterTrackingBinding
import com.needcode.rangkirku.network.response.WaybillResponse

class TrackingResultAdapter(
    val waybilles: List<WaybillResponse.Rajaongkir.Results.Manifest>
) : RecyclerView.Adapter<TrackingResultAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterTrackingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val waybill = waybilles[position]
        holder.binding.textDate.text = "${waybill.manifest_date} ${waybill.manifest_time}"
        holder.binding.textDescription.text = waybill.manifest_description
    }

    override fun getItemCount(): Int = waybilles.size

    class ViewHolder(val binding: AdapterTrackingBinding) : RecyclerView.ViewHolder(binding.root)

}