package com.kalu.repository

import com.kalu.network.EndPoints

class DisasterRepository(
    private val api: EndPoints
) : BaseRepository() {

    suspend fun getDisasters() = safeApiCall { api.getAllDisasters() }
}
