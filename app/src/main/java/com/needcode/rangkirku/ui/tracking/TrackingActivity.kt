package com.needcode.rangkirku.ui.tracking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.databinding.ActivityTrackingBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class TrackingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private lateinit var viewModel: TrackingViewModel
    private val viewModelFactory: TrackingViewModelFactory by instance()

    private val binding by lazy {
        ActivityTrackingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

        }

        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(TrackingViewModel::class.java)
    }

    private fun setupView() {
        supportActionBar?.title = "Lacak Nomer Resi"
    }


    override fun onBackPressed() {
        if (intent.getBooleanExtra("is_tracking", false)) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}