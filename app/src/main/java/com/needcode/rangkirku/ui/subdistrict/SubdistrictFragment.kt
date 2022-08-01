package com.needcode.rangkirku.ui.subdistrict

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.databinding.FragmentSubdistrictBinding
import com.needcode.rangkirku.network.Resource
import com.needcode.rangkirku.network.response.SubdistrictResponse
import com.needcode.rangkirku.ui.city.CityViewModel

class SubdistrictFragment : Fragment() {

    private lateinit var binding: FragmentSubdistrictBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(CityViewModel::class.java)
    }

    private val cityId by lazy {
        requireArguments().getString("city_id")
    }

    private val cityName by lazy {
        requireArguments().getString("city_name")
    }
    private val type by lazy {
        requireActivity().intent.getStringExtra("type")
    }


    private lateinit var subdistrictAdapter: SubdistrictAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubdistrictBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("LogNotTimber")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        with(binding) {
            Log.e("TAG", "city id: $cityId")
            Log.e("TAG", "city id: $cityName")
        }

        setupView()
        setupObserver()
        setupListener()
        setupRecyclerView()
    }


    private fun setupView() {
        viewModel.titleBar.postValue("Pilih Kecamatan")
    }

    @SuppressLint("LogNotTimber")
    private fun setupObserver() {
        viewModel.subdistrictResponse.observe(viewLifecycleOwner) { data ->
            when (data) {
                is Resource.Loading -> {
                    Log.e("TAG", "Loading ... ")
                    with(binding) {
                        refreshSubdistrict.isRefreshing = true
                    }
                }
                is Resource.Success -> {
                    Log.e("TAG", "data subdistrict: ${data.data?.rajaongkir?.results}")
                    subdistrictAdapter.addData(data.data?.rajaongkir?.results!!)
                    with(binding) {
                        refreshSubdistrict.isRefreshing = false
                    }
                }
                is Resource.Error -> {
                    Log.e("TAG", "Eror: ${data.message}")
                    with(binding) {
                        refreshSubdistrict.isRefreshing = false
                    }
                }
            }
        }
    }

    private fun setupListener() {
        with(binding) {
            refreshSubdistrict.setOnRefreshListener {
                viewModel.fetchSubdistrict(cityId!!)
            }
        }
    }

    private fun setupRecyclerView() {
        subdistrictAdapter =
            SubdistrictAdapter(arrayListOf(), object : SubdistrictAdapter.OnAdapterListener {
                override fun onClick(result: SubdistrictResponse.Rajaongkir.Results) {
                    viewModel.save(
                        type = type!!,
                        id = result.subdistrict_id,
                        name = "${cityName!!}, ${result.subdistrict_name}"
                    )

                    requireActivity().finish()
                }

            })

        with(binding) {
            listSubdistrict.adapter = subdistrictAdapter
        }
    }

}