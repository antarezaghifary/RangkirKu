package com.needcode.rangkirku.ui.waybill

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.needcode.rangkirku.databinding.FragmentWayBillBinding
import com.needcode.rangkirku.ui.tracking.TrackingActivity

class WayBillFragment : Fragment() {

    private lateinit var binding: FragmentWayBillBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWayBillBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListner()
    }

    private fun setupListner() {
        with(binding) {
            editWaybill.setOnClickListener {
                startActivity(Intent(requireActivity(), TrackingActivity::class.java))
            }
        }
    }
}