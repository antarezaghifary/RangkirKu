package com.needcode.rangkirku.ui.city

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.databinding.ActivityCityBinding
import com.needcode.rangkirku.network.ApiService

class CityActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCityBinding.inflate(layoutInflater)
    }

    private val api by lazy { ApiService.getClient() }
    private lateinit var viewModelFactory: CityViewModelFactory
    private lateinit var viewModel: CityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViewModel()

        viewModel.titleBar.observe(this, Observer { titleBar ->
            supportActionBar?.title = titleBar
        })

        with(binding) {

        }
    }

    private fun setupViewModel() {
        viewModelFactory = CityViewModelFactory(api)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CityViewModel::class.java)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}