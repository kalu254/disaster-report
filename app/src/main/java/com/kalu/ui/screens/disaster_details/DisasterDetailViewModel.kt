package com.kalu.ui.screens.disaster_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kalu.models.Disaster

class DisasterDetailViewModel(private val disaster: Disaster, private val app: Application) : AndroidViewModel(app) {

    private val _selectedDisaster = MutableLiveData<Disaster>()

    val selectedDisaster: LiveData<Disaster>
        get() = _selectedDisaster

    init {
        _selectedDisaster.value = disaster
    }
}
