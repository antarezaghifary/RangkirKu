package com.needcode.rangkirku.ui.city

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.needcode.rangkirku.databinding.ActivityCityBinding

class CityActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

        }
    }
}