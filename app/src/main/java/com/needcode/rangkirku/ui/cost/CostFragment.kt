package com.needcode.rangkirku.ui.cost

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.needcode.rangkirku.databinding.FragmentCostBinding
import com.needcode.rangkirku.ui.city.CityActivity

class CostFragment : Fragment() {

    private lateinit var binding: FragmentCostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            editOrigin.setOnClickListener {
                startActivity(Intent(context, CityActivity::class.java))
            }
        }
    }

}