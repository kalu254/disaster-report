package com.kalu.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kalu.repository.AuthRepository
import com.kalu.repository.DisasterRepository
import com.kalu.ui.view_models.AuthViewModel
import com.kalu.ui.view_models.DisasterFeedViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: Any?) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(DisasterFeedViewModel::class.java) -> DisasterFeedViewModel(repository as DisasterRepository) as T
            else -> throw IllegalArgumentException("View model class not found")
            }
        }
    }

