package com.needcode.rangkirku.ui.cost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.needcode.rangkirku.database.preferences.PreferencesModel
import com.needcode.rangkirku.network.RangkirRepository

class CostViewModel(
    val repository: RangkirRepository
) : ViewModel() {
    val preferencesModel: MutableLiveData<List<PreferencesModel>> = MutableLiveData()

    fun getPreferences() {
        preferencesModel.value = repository.getPreferences()
    }
}