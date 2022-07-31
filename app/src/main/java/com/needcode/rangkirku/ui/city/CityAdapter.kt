package com.needcode.rangkirku.ui.city

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.needcode.rangkirku.databinding.AdapterCityBinding
import com.needcode.rangkirku.network.response.CityResponse

class CityAdapter(
    val cities: ArrayList<CityResponse.Rajaongkir.Results>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CityAdapter.ViewHolder, position: Int) {
        val city = cities[position]
        holder.binding.textName.text = city.city_name
        holder.binding.container.setOnClickListener {
            listener.onClick(city)
        }
    }

    override fun getItemCount(): Int = cities.size

    class ViewHolder(val binding: AdapterCityBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnAdapterListener {
        fun onClick(result: CityResponse.Rajaongkir.Results)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(data: List<CityResponse.Rajaongkir.Results>) {
        cities.clear()
        cities.addAll(data)
        notifyDataSetChanged()
    }
}