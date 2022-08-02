package com.needcode.rangkirku.ui.trackingresult

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.databinding.FragmentTrackingResultBinding
import com.needcode.rangkirku.network.Resource
import com.needcode.rangkirku.ui.tracking.TrackingViewModel
import timber.log.Timber

class TrackingResultFragment : Fragment() {

    private lateinit var binding: FragmentTrackingResultBinding
    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(TrackingViewModel::class.java)
    }

    private val waybill by lazy {
        requireArguments().getString("waybill")
    }

    private val courier by lazy {
        requireArguments().getString("courier")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrackingResultBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

        }

        setupObserver()
    }

    @SuppressLint("LogNotTimber", "TimberArgCount")
    private fun setupObserver() {
        viewModel.waybillResponse.observe(viewLifecycleOwner) { data ->
            when (data) {
                is Resource.Loading -> {
                    Log.e("TAG", "Loading ... ")
                    with(binding) {
                    }
                }
                is Resource.Success -> {
                    Timber.e("data waybill: ${data.data?.rajaongkir?.result?.manifest}")
                    with(binding) {
                    }
                }
                is Resource.Error -> {
                    Log.e("TAG", "Eror: ${data.message}")
                    with(binding) {
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.postWaybill(
            waybill!!,
            courier!!
        )
    }
}