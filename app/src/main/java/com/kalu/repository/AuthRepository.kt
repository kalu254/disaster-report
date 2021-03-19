package com.kalu.repository

import com.kalu.models.UserAuthorization
import com.kalu.network.UserAuthApi

class AuthRepository(
    private val api: UserAuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(
        userAuthorization: UserAuthorization
    ) = safeApiCall {
        api.login(userAuthorization)
    }

    suspend fun saveToken(token: String) {
        preferences.saveAuthToken(token)
    }

    suspend fun saveUser(id: Int, username: String, email: String){
        preferences.saveUser(id,username,email)
    }
}


