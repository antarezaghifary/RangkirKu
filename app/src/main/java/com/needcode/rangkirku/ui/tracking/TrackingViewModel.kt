package com.needcode.rangkirku.ui.tracking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.needcode.rangkirku.network.RangkirRepository
import com.needcode.rangkirku.network.Resource
import com.needcode.rangkirku.network.response.WaybillResponse
import kotlinx.coroutines.launch
import timber.log.Timber

class TrackingViewModel(
    val repository: RangkirRepository
) : ViewModel() {
    val waybillResponse: MutableLiveData<Resource<WaybillResponse>> = MutableLiveData()

    fun postWaybill(
        waybill: String,
        courier: String
    ) = viewModelScope.launch {
        waybillResponse.value = Resource.Loading()
        try {
            val response = repository.waybill(
                waybill,
                courier
            )
            waybillResponse.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            waybillResponse.value = Resource.Error(e.message.toString())
            Timber.e(e)
        }
    }
}