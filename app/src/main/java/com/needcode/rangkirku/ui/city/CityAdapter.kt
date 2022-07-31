package com.needcode.rangkirku.ui.city

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.needcode.rangkirku.databinding.AdapterCityBinding
import com.needcode.rangkirku.network.response.CityResponse
import timber.log.Timber
import java.util.*

class CityAdapter(
    val cities: ArrayList<CityResponse.Rajaongkir.Results>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<CityAdapter.ViewHolder>(), Filterable {
    private var citiesFilter = ArrayList<CityResponse.Rajaongkir.Results>()

    init {
        citiesFilter = cities
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CityAdapter.ViewHolder, position: Int) {
        val city = citiesFilter[position]
        holder.binding.textName.text = city.city_name
        holder.binding.container.setOnClickListener {
            listener.onClick(city)
        }
    }

    override fun getItemCount(): Int = citiesFilter.size

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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                Timber.d("charSearch: $charSearch")
                if (charSearch.isEmpty()) {
                    citiesFilter = cities
                } else {
                    val citiesFiltered = ArrayList<CityResponse.Rajaongkir.Results>()
                    for (city in cities) {
                        if (city.city_name.lowercase(Locale.getDefault())
                                .contains(charSearch.lowercase(Locale.getDefault()))
                        ) {
                            citiesFiltered.add(city)
                        }
                    }
                    citiesFilter = citiesFiltered
                }
                val citiesFilteredResult = FilterResults()
                citiesFilteredResult.values = citiesFilter
                return citiesFilteredResult
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                citiesFilter = results?.values as ArrayList<CityResponse.Rajaongkir.Results>
                notifyDataSetChanged()
            }

        }
    }
}