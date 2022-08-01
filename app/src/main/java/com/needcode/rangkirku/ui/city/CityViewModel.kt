package com.needcode.rangkirku.ui.city

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.needcode.rangkirku.network.RangkirRepository
import com.needcode.rangkirku.network.Resource
import com.needcode.rangkirku.network.response.CityResponse
import com.needcode.rangkirku.network.response.SubdistrictResponse
import kotlinx.coroutines.launch

class CityViewModel(
    val repository: RangkirRepository
) : ViewModel() {
    val titleBar: MutableLiveData<String> = MutableLiveData("")
    val cityResponse: MutableLiveData<Resource<CityResponse>> = MutableLiveData()
    val subdistrictResponse: MutableLiveData<Resource<SubdistrictResponse>> = MutableLiveData()


    //Main
    init {
        fetchCity()
    }

    @SuppressLint("LogNotTimber")
    fun fetchCity() = viewModelScope.launch {
        cityResponse.value = Resource.Loading()

        try {
            cityResponse.value = Resource.Success(repository.city().body()!!)
        } catch (e: Exception) {
            cityResponse.value = Resource.Error(e.message.toString())
        }

    }

    fun fetchSubdistrict(cityId: String) = viewModelScope.launch {
        subdistrictResponse.value = Resource.Loading()

        try {
            subdistrictResponse.value = Resource.Success(repository.subdistrict(cityId).body()!!)
        } catch (e: Exception) {
            subdistrictResponse.value = Resource.Error(e.message.toString())
        }
    }

    fun save(type: String, id: String, name: String) {
        repository.savePreferences(type, id, name)
    }
}