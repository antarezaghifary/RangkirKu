package com.needcode.rangkirku.ui.waybill

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.databinding.FragmentWayBillBinding
import com.needcode.rangkirku.ui.tracking.TrackingActivity
import com.needcode.rangkirku.ui.tracking.TrackingViewModel
import timber.log.Timber

class WayBillFragment : Fragment() {

    private lateinit var binding: FragmentWayBillBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(TrackingViewModel::class.java)
    }

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
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.waybill.observe(viewLifecycleOwner) {
            Timber.e("waybill $it")
        }
    }

    private fun setupListner() {
        with(binding) {
            editWaybill.setOnClickListener {
                startActivity(Intent(requireActivity(), TrackingActivity::class.java))
            }
        }
    }
}