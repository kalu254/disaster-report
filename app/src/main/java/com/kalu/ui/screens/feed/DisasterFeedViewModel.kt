package com.kalu.ui.view_models

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalu.models.Disaster
import com.kalu.network.Resource
import com.kalu.repository.DisasterRepository
import kotlinx.coroutines.launch

enum class TanaApiStatus { LOADING, ERROR, DONE }



class DisasterFeedViewModel(
    private val disasterRepository: DisasterRepository,
) : ViewModel() {

    private val _disasters : MutableLiveData<Resource<List<Disaster>>> = MutableLiveData()
    val disasters: MutableLiveData<Resource<List<Disaster>>>
    get() = _disasters

    private val _status = MutableLiveData<TanaApiStatus>()
    val status: LiveData<TanaApiStatus>
        get() = _status

    private val _navigateToSelectedDisaster = MutableLiveData<Disaster?>()

    val navigateToSelectedDisaster: MutableLiveData<Disaster?>
        get() = _navigateToSelectedDisaster

    fun displayDisasterDetails(disaster: Disaster) {
        _navigateToSelectedDisaster.value = disaster
    }

    fun displayDisasterDetailsComplete() {
        _navigateToSelectedDisaster.value = null
    }


    suspend fun getAllDisasters() {
        viewModelScope.launch {
            try {
                _disasters.value = disasterRepository.getDisasters()
                _status.value = TanaApiStatus.DONE

            }
            catch (e: Exception) {
                _status.value = TanaApiStatus.ERROR
            }
        }

    }


}
