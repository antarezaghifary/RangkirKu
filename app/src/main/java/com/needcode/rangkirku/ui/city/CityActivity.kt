package com.needcode.rangkirku.ui.city

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.databinding.ActivityCityBinding

class CityActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCityBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(CityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.titleBar.observe(this, Observer { titleBar ->
            supportActionBar?.title = titleBar
        })

        with(binding) {

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}