package com.needcode.rangkirku.ui.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.needcode.rangkirku.network.RajaOngkirEndPoint

class CityViewModelFactory(
    private val api: RajaOngkirEndPoint
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CityViewModel(api) as T
    }
}