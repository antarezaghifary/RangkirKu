package com.needcode.rangkirku.ui.city

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.needcode.rangkirku.R
import com.needcode.rangkirku.databinding.FragmentCityBinding
import com.needcode.rangkirku.network.Resource
import com.needcode.rangkirku.network.response.CityResponse

class CityFragment : Fragment() {

    private lateinit var binding: FragmentCityBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(CityViewModel::class.java)
    }

    private lateinit var cityAdapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListenerView()
        setupRecyclerView()
        setupObserver()

        with(binding) {

        }
    }

    private fun setupView() {
        with(binding) {
            viewModel.titleBar.postValue("Pilih Kota")
        }
    }


    private fun setupListenerView() {
        with(binding) {
            editSearch.doAfterTextChanged { data ->
                cityAdapter.filter.filter(
                    data.toString()
                )
            }

            refreshCity.setOnRefreshListener {
                viewModel.fetchCity()
            }
            container.setOnClickListener {
                findNavController().navigate(R.id.action_cityFragment_to_subdistrictFragment)
            }
        }
    }

    private fun setupRecyclerView() {
        cityAdapter = CityAdapter(arrayListOf(), object : CityAdapter.OnAdapterListener {
            override fun onClick(result: CityResponse.Rajaongkir.Results) {

            }
        })
        with(binding) {
            listCity.adapter = cityAdapter
        }
    }


    @SuppressLint("LogNotTimber")
    private fun setupObserver() {
        viewModel.cityResponse.observe(viewLifecycleOwner) { data ->
            when (data) {
                is Resource.Loading -> {
                    Log.e("TAG", "Loading ... ")
                    with(binding) {
                        refreshCity.isRefreshing = true
                    }
                }
                is Resource.Success -> {
                    Log.e("TAG", "data city: ${data.data?.rajaongkir}")
                    cityAdapter.addData(data.data?.rajaongkir?.results!!)
                    with(binding) {
                        refreshCity.isRefreshing = false
                    }
                }
                is Resource.Error -> {
                    Log.e("TAG", "Eror: ${data.message}")
                    with(binding) {
                        refreshCity.isRefreshing = false
                    }
                }
            }
        }
    }

}