package com.kalu.ui.screens.view_model_factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kalu.models.Disaster
import com.kalu.ui.screens.disaster_details.DisasterDetailViewModel

class DetailViewModelFactory (
    private val disaster: Disaster,
    private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DisasterDetailViewModel::class.java)) {
                return DisasterDetailViewModel(disaster, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
