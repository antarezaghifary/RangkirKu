package com.needcode.rangkirku.ui.tracking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.needcode.rangkirku.databinding.ActivityTrackingBinding

class TrackingActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityTrackingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

        }

        setupView()
    }

    private fun setupView() {
        supportActionBar?.title = "Lacak Nomer Resi"
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return super.onNavigateUp()
    }
}