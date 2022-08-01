package com.needcode.rangkirku.ui.tracking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.network.RangkirRepository

class TrackingViewModelFactory(
    private val repository: RangkirRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrackingViewModel(repository) as T
    }
}