package com.needcode.rangkirku.ui.city

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.databinding.ActivityCityBinding
import com.needcode.rangkirku.network.ApiService
import timber.log.Timber

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
                Timber.d("cityResponse: $data")
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}