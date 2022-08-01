package com.needcode.rangkirku.ui.cost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.network.RangkirRepository

class CostViewModelFactory(
    private val repository: RangkirRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CostViewModel(repository) as T
    }
}