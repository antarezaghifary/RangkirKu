package com.needcode.rangkirku.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.needcode.rangkirku.R
import com.needcode.rangkirku.databinding.FragmentCityBinding

class CityFragment : Fragment() {

    private lateinit var binding: FragmentCityBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(CityViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.titleBar.postValue("Pilih Kota")

        with(binding) {
            //val container = AdapterCityBinding.inflate(LayoutInflater.from(requireContext()))
            container.setOnClickListener {
                findNavController().navigate(R.id.action_cityFragment_to_subdistrictFragment)
            }
        }
    }

}