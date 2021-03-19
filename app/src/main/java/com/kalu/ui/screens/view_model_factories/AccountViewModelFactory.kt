package com.kalu.ui.screens.view_model_factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kalu.repository.UserPreferences
import com.kalu.ui.screens.account.AccountsViewModel

class AccountViewModelFactory(private val userPreferences: UserPreferences,
                              private val application: Application)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AccountsViewModel::class.java)){
            return AccountsViewModel(userPreferences,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
