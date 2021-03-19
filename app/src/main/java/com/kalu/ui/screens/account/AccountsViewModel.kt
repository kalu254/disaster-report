package com.kalu.ui.screens.account

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kalu.models.UserProfile
import com.kalu.repository.UserPreferences
import kotlinx.coroutines.flow.first

class AccountsViewModel(private val userPreferences: UserPreferences, app: Application) : AndroidViewModel(app) {

    private val _userProfile = MutableLiveData<UserProfile>()

    val userProfile: MutableLiveData<UserProfile>
        get() = _userProfile

    suspend fun getProfile(){
        _userProfile.value = userPreferences.userProfile.first()
    }

}

