package com.kalu.ui.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalu.repository.AuthRepository
import com.kalu.models.UserAuthorization
import com.kalu.network.Resource
import com.kalu.models.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {


    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: MutableLiveData<Resource<LoginResponse>>
        get() = _user

    fun login(userAuthorization: UserAuthorization) = viewModelScope.launch {
        _user.value = repository.login(userAuthorization)
    }

    fun saveAuthToken(token : String) = viewModelScope.launch {
        repository.saveToken(token)
    }
}
