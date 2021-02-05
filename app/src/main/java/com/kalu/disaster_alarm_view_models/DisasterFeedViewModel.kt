package com.kalu.disaster_alarm_view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalu.api_client.EndPoints
import com.kalu.api_client.ServiceBuilder
import com.kalu.models.Disaster
import kotlinx.coroutines.launch


class DisasterFeedViewModel : ViewModel() {

    val disasters = MutableLiveData<List<Disaster>>()


    suspend fun getAllDisasters() : LiveData<List<Disaster>> {
        viewModelScope.launch {
            try {
                disasters.value = ServiceBuilder.buildService(EndPoints::class.java).getAllDisasters()
            }
            catch (e: Exception) {
                Log.d("What happened?","This is what happened:  ${e}")
            }
        }

        return disasters
    }


}
