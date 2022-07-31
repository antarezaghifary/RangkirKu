package com.needcode.rangkirku.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.needcode.rangkirku.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

        }

        setupTab()
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