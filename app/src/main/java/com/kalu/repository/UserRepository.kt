package com.kalu.repository

import com.kalu.network.EndPoints
import com.kalu.network.UserAuthApi

class UserRepository(
    private val api: EndPoints
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getChiefs()
    }
}
