package com.needcode.rangkirku.ui.cost

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.databinding.FragmentCostBinding
import com.needcode.rangkirku.ui.city.CityActivity

class CostFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(CostViewModel::class.java)
    }
    private lateinit var binding: FragmentCostBinding

    private var originId: String? = ""
    private var destinationId: String? = ""

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


        setupObserver()
        setupListener()
    }

    private fun setupListener() {
        with(binding) {
            editOrigin.setOnClickListener {
                startActivity(
                    Intent(context, CityActivity::class.java)
                        .putExtra("type", "origin")
                )
            }

            editDestination.setOnClickListener {
                startActivity(
                    Intent(context, CityActivity::class.java)
                        .putExtra("type", "destination")
                )
            }
        }
    }

    private fun setupObserver() {

        with(binding) {
            viewModel.preferencesModel.observe(viewLifecycleOwner) { preferencesList ->
                preferencesList.forEach {
                    when (it.type) {
                        "origin" -> {
                            originId = it.id
                            editOrigin.setText(it.name)
                        }
                        "destination" -> {
                            destinationId = it.id
                            editDestination.setText(it.name)
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPreferences()
    }

}