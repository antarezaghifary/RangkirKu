package com.needcode.rangkirku.ui.cost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.needcode.rangkirku.database.preferences.PreferencesModel
import com.needcode.rangkirku.network.RangkirRepository
import com.needcode.rangkirku.network.Resource
import com.needcode.rangkirku.network.response.CostResponse
import kotlinx.coroutines.launch
import timber.log.Timber

class CostViewModel(
    val repository: RangkirRepository
) : ViewModel() {
    val preferencesModel: MutableLiveData<List<PreferencesModel>> = MutableLiveData()
    val costResponse: MutableLiveData<Resource<CostResponse>> = MutableLiveData()

    fun getPreferences() {
        preferencesModel.value = repository.getPreferences()
    }

    fun postCost(
        origin: String,
        originType: String,
        destination: String,
        destinationType: String,
        weight: String,
        courier: String
    ) = viewModelScope.launch {
        costResponse.value = Resource.Loading()
        try {
            val response = repository.cost(
                origin,
                originType,
                destination,
                destinationType,
                weight,
                courier
            )
            costResponse.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            costResponse.value = Resource.Error(e.message.toString())
            Timber.e(e)
        }
    }
}