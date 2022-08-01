package com.needcode.rangkirku.ui.city

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.databinding.ActivityCityBinding
import com.needcode.rangkirku.network.Resource
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class CityActivity : AppCompatActivity(), KodeinAware {

    private val binding by lazy {
        ActivityCityBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: CityViewModel
    override val kodein by kodein()
    private val viewModelFactory: CityViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViewModel()
        setupObservable()


        with(binding) {

        }
    }


    private fun setupViewModel() {
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