package com.needcode.rangkirku.ui.city

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.needcode.rangkirku.network.RajaOngkirEndPoint
import com.needcode.rangkirku.network.response.CityResponse
import kotlinx.coroutines.launch

class CityViewModel(
    val api: RajaOngkirEndPoint
) : ViewModel() {
    val titleBar: MutableLiveData<String> = MutableLiveData("")
    val cityResponse: MutableLiveData<CityResponse> = MutableLiveData()

    //Main
    init {
        fetchCity()
    }

    @SuppressLint("LogNotTimber")
    fun fetchCity() = viewModelScope.launch {
        cityResponse.value = api.city().body()
    }
}