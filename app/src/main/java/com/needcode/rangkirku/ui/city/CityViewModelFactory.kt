package com.needcode.rangkirku.ui.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.network.RangkirRepository

class CityViewModelFactory(
    private val repository: RangkirRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CityViewModel(repository) as T
    }
}