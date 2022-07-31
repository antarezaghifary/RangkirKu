package com.needcode.rangkirku.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.needcode.rangkirku.network.RajaOngkirEndPoint

class CityViewModel(
    val api: RajaOngkirEndPoint
) : ViewModel() {
    val titleBar: MutableLiveData<String> = MutableLiveData("")

}