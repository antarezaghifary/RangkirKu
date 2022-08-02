package com.needcode.rangkirku.ui.tracking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.needcode.rangkirku.database.persistence.WaybillEntity
import com.needcode.rangkirku.network.RangkirRepository
import com.needcode.rangkirku.network.Resource
import com.needcode.rangkirku.network.response.WaybillResponse
import kotlinx.coroutines.launch
import timber.log.Timber

class TrackingViewModel(
    val repository: RangkirRepository
) : ViewModel() {
    val waybillResponse: MutableLiveData<Resource<WaybillResponse>> = MutableLiveData()
    val waybill: LiveData<List<WaybillEntity>> = repository.getData()

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
            saveData(response.body()!!.rajaongkir)
        } catch (e: Exception) {
            waybillResponse.value = Resource.Error(e.message.toString())
            Timber.e(e)
        }
    }

    fun saveData(data: WaybillResponse.Rajaongkir) = viewModelScope.launch {
        repository.insertData(
            WaybillEntity(
                waybill = data.query.waybill,
                courier = data.query.courier,
                status = data.result.delivery_status.status
            )
        )
    }

    fun deleteData(data: WaybillEntity) = viewModelScope.launch {
        repository.deleteData(
            data
        )
    }
}