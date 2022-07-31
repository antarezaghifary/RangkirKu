package com.needcode.rangkirku.ui.city

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.databinding.ActivityCityBinding
import com.needcode.rangkirku.network.ApiService
import com.needcode.rangkirku.network.Resource

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
        setupObservable()


        with(binding) {

        }
    }


    private fun setupViewModel() {
        viewModelFactory = CityViewModelFactory(api)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CityViewModel::class.java)
    }

    @SuppressLint("LogNotTimber")
    private fun setupObservable() {
        with(binding) {
            viewModel.titleBar.observe(this@CityActivity) { titleBar ->
                supportActionBar?.title = titleBar
            }
            viewModel.cityResponse.observe(this@CityActivity) { data ->
                when (data) {
                    is Resource.Loading -> {
                        Log.e("TAG", "Loading ... ")
                    }

                    is Resource.Success -> {
                        Log.e("TAG", "data city: ${data.data?.rajaongkir}")
                    }

                    is Resource.Error -> {
                        Log.e("TAG", "Eror: ${data.message}")
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}