package com.needcode.rangkirku.ui.tracking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.needcode.rangkirku.R
import com.needcode.rangkirku.databinding.FragmentTrackingBinding

class TrackingFragment : Fragment() {

    private lateinit var binding: FragmentTrackingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrackingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

        }

        setupListener()
    }

    private fun setupListener() {
        val courierAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.courier,
            android.R.layout.simple_spinner_item
        )

        courierAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.listCourier.adapter = courierAdapter

        binding.buttonTrack.setOnClickListener {
            findNavController().navigate(
                R.id.action_trackingFragment_to_trackingResultFragment,
                bundleOf(
                    "waybill" to binding.editWaybill.text.toString(),
                    "courier" to binding.listCourier.selectedItem.toString()
                )
            )
        }
    }
}