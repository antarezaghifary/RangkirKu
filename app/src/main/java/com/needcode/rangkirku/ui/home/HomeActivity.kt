package com.needcode.rangkirku.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.needcode.rangkirku.databinding.ActivityHomeBinding
import com.needcode.rangkirku.ui.cost.CostViewModel
import com.needcode.rangkirku.ui.cost.CostViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity(), KodeinAware {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: CostViewModel
    override val kodein by kodein()
    private val viewModelFactory: CostViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

        }

        setupTab()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(CostViewModel::class.java)
    }

    private fun setupTab() {
        val title = arrayListOf("CEK ONGKIR", "CEK RESI")
        val tabAdapter = HomeTabAdapter(supportFragmentManager, lifecycle)

        with(binding) {
            viewPager.adapter = tabAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = title[position]
            }.attach()
        }

    }
}