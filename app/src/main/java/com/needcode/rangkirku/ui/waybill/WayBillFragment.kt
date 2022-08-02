package com.needcode.rangkirku.ui.waybill

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.database.persistence.WaybillEntity
import com.needcode.rangkirku.databinding.FragmentWayBillBinding
import com.needcode.rangkirku.ui.tracking.TrackingActivity
import com.needcode.rangkirku.ui.tracking.TrackingViewModel
import timber.log.Timber

class WayBillFragment : Fragment() {

    private lateinit var binding: FragmentWayBillBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(TrackingViewModel::class.java)
    }

    private lateinit var waybillAdapter: WaybillAdapter

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
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        waybillAdapter = WaybillAdapter(arrayListOf(), object : WaybillAdapter.OnAdapterListener {
            override fun onDetail(result: WaybillEntity) {
                startActivity(
                    Intent(requireActivity(), TrackingActivity::class.java)
                        .putExtra("is_tracking", true)
                        .putExtra("is_waybill", result.waybill)
                        .putExtra("is_courier", result.courier)
                )
            }

            override fun onDelete(result: WaybillEntity) {
                val builder = AlertDialog.Builder(requireActivity())
                builder.apply {
                    setTitle("Hapus Resi")
                    setMessage("Hapus Nomor Resi: ${result.waybill}?")
                    setPositiveButton("Hapus") { _, _ ->
                        viewModel.deleteData(result)
                        Toast.makeText(requireActivity(), "Resi Dihapus", Toast.LENGTH_SHORT).show()
                    }

                    setNegativeButton("Batal") { _, _ ->

                    }
                }

                builder.show()
            }

        })
        binding.listWaybill.adapter = waybillAdapter
    }

    private fun setupObserver() {
        viewModel.waybill.observe(viewLifecycleOwner) {
            Timber.e("waybill $it")
            waybillAdapter.addData(it)
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